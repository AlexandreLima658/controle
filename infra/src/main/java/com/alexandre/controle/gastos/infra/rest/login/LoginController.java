package com.alexandre.controle.gastos.infra.rest.login;


import com.alexandre.controle.gastos.application.login.LoginInput;
import com.alexandre.controle.gastos.application.login.LoginOutput;
import com.alexandre.controle.gastos.application.login.LoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginAPI{

    private final LoginUseCase loginUseCase;

    public LoginController(final LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Override
    public ResponseEntity<LoginOutput> login(final LoginInput input) {
        return ResponseEntity.ok(this.loginUseCase.execute(input));
    }
}
