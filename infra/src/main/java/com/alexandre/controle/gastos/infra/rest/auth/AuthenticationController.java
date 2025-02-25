package com.alexandre.controle.gastos.infra.rest.auth;


import com.alexandre.controle.gastos.application.auth.commands.login.LoginInput;
import com.alexandre.controle.gastos.application.auth.commands.login.LoginOutput;
import com.alexandre.controle.gastos.application.auth.commands.login.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationAPI {

    private final LoginUseCase loginUseCase;

    public AuthenticationController(final LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Override
    public ResponseEntity<LoginOutput> login(final LoginInput input) {
        return ResponseEntity.ok(this.loginUseCase.execute(input));
    }

}
