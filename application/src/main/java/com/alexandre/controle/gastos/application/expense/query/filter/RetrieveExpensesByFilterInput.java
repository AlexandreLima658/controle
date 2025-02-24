package com.alexandre.controle.gastos.application.expense.query.filter;

public record RetrieveExpensesByFilterInput(
        int page,
        int perPage,
        String sortBy,
        String sortDirection
) {
}
