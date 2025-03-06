package com.alexandre.controle.gastos.application.user.commands.create;

public record CreateUserInput(
        String name,
        String email,
        String password,
        String role
) {
}
