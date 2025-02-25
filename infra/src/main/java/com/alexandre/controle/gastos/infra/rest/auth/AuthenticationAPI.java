package com.alexandre.controle.gastos.infra.rest.auth;

import com.alexandre.controle.gastos.application.auth.commands.login.LoginInput;
import com.alexandre.controle.gastos.application.auth.commands.login.LoginOutput;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "auth")
@Tag(name = "Authentication", description = "Authentication")
public interface AuthenticationAPI {

    @PostMapping(value = "/login")
    ResponseEntity<LoginOutput> login (@RequestBody LoginInput input);

}
