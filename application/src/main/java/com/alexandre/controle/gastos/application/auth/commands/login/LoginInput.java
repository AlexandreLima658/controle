package com.alexandre.controle.gastos.application.auth.commands.login;

public record LoginInput(
        String name,
        String password
) {
}
