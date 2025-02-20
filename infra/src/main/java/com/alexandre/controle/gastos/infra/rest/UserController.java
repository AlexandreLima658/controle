package com.alexandre.controle.gastos.infra.rest;


import com.alexandre.controle.gastos.application.user.commands.create.CreateUserInput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserUseCase;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUseByFilterInput;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUserByFilterOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.gateways.user.RetrieveUseByFilterGatewayImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController implements UserAPI{

    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUseByFilterGatewayImpl retrieveUseByFilterGateway;

    public UserController(
            final CreateUserUseCase createUserUseCase,
            final RetrieveUseByFilterGatewayImpl retrieveUseByFilterGateway
    ) {
        this.createUserUseCase = createUserUseCase;
        this.retrieveUseByFilterGateway = retrieveUseByFilterGateway;
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
}
