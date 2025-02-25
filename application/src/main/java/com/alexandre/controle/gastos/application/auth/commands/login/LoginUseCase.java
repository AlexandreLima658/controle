package com.alexandre.controle.gastos.application.auth.commands.login;

import com.alexandre.controle.gastos.application.UseCase;
import jakarta.inject.Named;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
public class LoginUseCase extends UseCase<LoginInput,LoginOutput> {

    private final AuthenticationManager manager;

    public LoginUseCase(final AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    public LoginOutput execute(final LoginInput input) {

        final var userPassword = new UsernamePasswordAuthenticationToken(
                input.name(),
                input.password()
        );

       final var id = this.manager.authenticate(userPassword);

        return new LoginOutput(id.getName());

    }
}
