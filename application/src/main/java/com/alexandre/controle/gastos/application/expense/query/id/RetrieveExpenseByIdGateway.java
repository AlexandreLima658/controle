package com.alexandre.controle.gastos.application.expense.query.id;

public interface RetrieveExpenseByIdGateway {
    RetrieveExpenseByIdOutput execute(final Long id);
}
