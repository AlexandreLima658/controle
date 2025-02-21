package com.alexandre.controle.gastos.application.user.query.filter;

public record RetrieveUsersByFilterInput(
        int page,
        int perPage,
        String sortBy,
        String sortDirection
) {
}
