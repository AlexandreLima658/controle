package com.alexandre.controle.gastos.application.category.query.filter;

public record RetrieveCategoriesByFilterInput(
        int page,
        int perPage,
        String sortBy,
        String sortDirection
) {
}
