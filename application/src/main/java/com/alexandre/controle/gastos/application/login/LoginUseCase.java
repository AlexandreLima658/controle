package com.alexandre.controle.gastos.application.login;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import jakarta.inject.Named;

@Named
public class LoginUseCase extends UseCase<LoginInput, LoginOutput> {

    private final UserRepository repository;

    public LoginUseCase(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginOutput execute(final LoginInput input) {

        final var user = repository.findByEmailAndPassword(new Email(input.email()), input.password())
                .orElseThrow(() -> DomainException.with("Login or password not found"));

        return new LoginOutput(input.email());
    }
}
