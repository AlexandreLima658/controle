package com.alexandre.controle.gastos.application.user.commands.create;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.user.UserFactory;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import com.alexandre.controle.gastos.domain.user.attributes.UserName;
import jakarta.inject.Named;

@Named
public class CreateUserUseCase extends UseCase<CreateUserInput, CreateUserOutput> {

    private final UserRepository repository;

    public CreateUserUseCase(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateUserOutput execute(final CreateUserInput input) {

        final var newUser = UserFactory.create(
                new UserName(input.name()),
                new Email(input.email()),
                input.password()
        );

        final var userId = this.repository.persist(newUser);

        return new CreateUserOutput(userId.value());
    }
}
