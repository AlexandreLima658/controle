package com.alexandre.controle.gastos.application.category.query.id;

public record RetrieveCategoryByIdOutput(
        Long id,
        String name,
        String description
) {
}
