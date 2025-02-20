package com.alexandre.controle.gastos.application.user.commands.update;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import com.alexandre.controle.gastos.domain.user.User;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import jakarta.inject.Named;

@Named
public class UpdateUserUseCase extends UseCase<UpdateUserInput, UpdateUserOutput> {

    private final UserRepository repository;

    public UpdateUserUseCase(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateUserOutput execute(final UpdateUserInput input) {

        final var userId = UserId.from(input.id());

        final var user = repository
                .findById(userId)
                .orElseThrow(() -> NotFoundException.with(User.class, userId));

        user.update(
                input.name(),
                input.email(),
                input.password()
        );

        final var id = this.repository.persist(user);

        return new UpdateUserOutput(id.value());
    }
}
