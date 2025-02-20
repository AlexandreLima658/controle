package com.alexandre.controle.gastos.application.user.commands.create;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.user.UserFactory;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import jakarta.inject.Named;

@Named
public class CreateUserUseCase extends UseCase<CreateUserInput, CreateUserOutput> {

    private final UserRepository repository;

    public CreateUserUseCase(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateUserOutput execute(final CreateUserInput input) {

        final var user = UserFactory.create(
                input.name(),
                input.email(),
                input.password()
        );

        final var userId = this.repository.persist(user);

        return new CreateUserOutput(userId.value());
    }
}
