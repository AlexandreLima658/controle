package com.alexandre.controle.gastos.application.expense.query.filter;

import com.alexandre.controle.gastos.domain.pagination.Pagination;

public interface RetrieveExpenseByFilterGateway {

    Pagination<RetrieveExpensesByFilterOutput> execute(final RetrieveExpensesByFilterInput input);
}
