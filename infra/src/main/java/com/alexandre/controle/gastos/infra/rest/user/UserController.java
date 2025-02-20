package com.alexandre.controle.gastos.infra.rest.user;


import com.alexandre.controle.gastos.application.user.commands.create.CreateUserInput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserUseCase;
import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserUseCase;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUseByFilterInput;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUserByFilterOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.gateways.user.RetrieveUseByFilterGatewayImpl;
import com.alexandre.controle.gastos.infra.rest.user.models.UpdateUserHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController implements UserAPI{

    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUseByFilterGatewayImpl retrieveUseByFilterGateway;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(
            final CreateUserUseCase createUserUseCase,
            final RetrieveUseByFilterGatewayImpl retrieveUseByFilterGateway,
            final UpdateUserUseCase updateUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.retrieveUseByFilterGateway = retrieveUseByFilterGateway;
        this.updateUserUseCase = updateUserUseCase;
    }

    @Override
    @Transactional
    public ResponseEntity<CreateUserOutput> create(final CreateUserInput input) {

        final var out = this.createUserUseCase.execute(input);

        final var uri = "/users/" + out.id();

        return ResponseEntity.created(URI.create(uri)).body(out);
    }

    @Override
    @Transactional
    public ResponseEntity<Pagination<RetrieveUserByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {

        final var input = new RetrieveUseByFilterInput(
                page,
                perPage,
                sort,
                direction
        );

        return ResponseEntity.ok(this.retrieveUseByFilterGateway.execute(input));
    }

    @Override
    public ResponseEntity<UpdateUserOutput> update(final Long userId, final UpdateUserHttpRequest request) {

        final var updateInput = request.toInput(userId);

        return ResponseEntity.ok(this.updateUserUseCase.execute(updateInput));
    }
}
