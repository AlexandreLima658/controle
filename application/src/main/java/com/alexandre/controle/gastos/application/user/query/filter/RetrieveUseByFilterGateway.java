package com.alexandre.controle.gastos.application.user.query.filter;

import com.alexandre.controle.gastos.domain.pagination.Pagination;

public interface RetrieveUseByFilterGateway {

    Pagination<RetrieveUserByFilterOutput> execute(final RetrieveUseByFilterInput input);
}
