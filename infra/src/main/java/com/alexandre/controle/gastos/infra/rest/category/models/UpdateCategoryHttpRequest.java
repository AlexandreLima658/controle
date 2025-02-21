package com.alexandre.controle.gastos.infra.rest.category.models;

import com.alexandre.controle.gastos.application.category.commands.update.UpdateCategoryInput;

public record UpdateCategoryHttpRequest(
        String name,
        String description
) {
    public UpdateCategoryInput toInput(final Long id){

        return new UpdateCategoryInput(
                id,
                name,
                description
        );
    }
}
