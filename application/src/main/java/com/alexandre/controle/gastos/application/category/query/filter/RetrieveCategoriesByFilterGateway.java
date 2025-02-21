package com.alexandre.controle.gastos.application.category.query.filter;

import com.alexandre.controle.gastos.domain.pagination.Pagination;

public interface RetrieveCategoriesByFilterGateway {

    Pagination<RetrieveCategoriesByFilterOutput> execute(final RetrieveCategoriesByFilterInput input);
}
