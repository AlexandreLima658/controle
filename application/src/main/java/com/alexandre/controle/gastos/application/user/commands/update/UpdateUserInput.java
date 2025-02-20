package com.alexandre.controle.gastos.application.user.commands.update;

public record UpdateUserInput(
        Long id,
        String name,
        String email,
        String password
) {
}
