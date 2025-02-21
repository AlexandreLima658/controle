package com.alexandre.controle.gastos.infra.rest.user;


import com.alexandre.controle.gastos.application.user.commands.create.CreateUserInput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserUseCase;
import com.alexandre.controle.gastos.application.user.commands.delete.DeleteUserUseCase;
import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserUseCase;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUsersByFilterInput;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUsersByFilterOutput;
import com.alexandre.controle.gastos.application.user.query.id.RetrieveUserByIdOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.gateways.user.RetrieveUsersByFilterGatewayImpl;
import com.alexandre.controle.gastos.infra.gateways.user.RetrieveUserByIdGatewayImpl;
import com.alexandre.controle.gastos.infra.rest.user.models.UpdateUserHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController implements UserAPI{

    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUsersByFilterGatewayImpl retrieveUseByFilterGateway;
    private final RetrieveUserByIdGatewayImpl retrieveUserByIdGateway;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            final CreateUserUseCase createUserUseCase,
            final RetrieveUsersByFilterGatewayImpl retrieveUseByFilterGateway,
            final RetrieveUserByIdGatewayImpl retrieveUserByIdGateway,
            final UpdateUserUseCase updateUserUseCase,
            final DeleteUserUseCase deleteUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.retrieveUseByFilterGateway = retrieveUseByFilterGateway;
        this.retrieveUserByIdGateway = retrieveUserByIdGateway;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
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
    public ResponseEntity<Pagination<RetrieveUsersByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {

        final var input = new RetrieveUsersByFilterInput(
                page,
                perPage,
                sort,
                direction
        );

        return ResponseEntity.ok(this.retrieveUseByFilterGateway.execute(input));
    }

    @Override
    public ResponseEntity<RetrieveUserByIdOutput> retrieveById(final Long userId) {
        return ResponseEntity.ok(this.retrieveUserByIdGateway.execute(userId));
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateUserOutput> update(final Long userId, final UpdateUserHttpRequest request) {

        final var updateInput = request.toInput(userId);

        return ResponseEntity.ok(this.updateUserUseCase.execute(updateInput));
    }

    @Override
    @Transactional
    public void delete(final Long userId) {
        this.deleteUserUseCase.execute(userId);
    }
}
