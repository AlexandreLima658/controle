package com.alexandre.controle.gastos.infra.rest.user.models;

import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserInput;

public record UpdateUserHttpRequest(
        String name,
        String email,
        String password
) {
    public UpdateUserInput toInput(final Long id){
        return new UpdateUserInput(
                id,
                name,
                email,
                password
        );
    }
}
