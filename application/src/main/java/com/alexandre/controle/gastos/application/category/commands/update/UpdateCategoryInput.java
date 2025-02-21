package com.alexandre.controle.gastos.application.category.commands.update;

public record UpdateCategoryInput(
        Long id,
        String name,
        String description
) {
}
