package com.alexandre.controle.gastos.domain.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(long currentPage, long perPage, long total, List<T> items){
    public <R> Pagination<R> map(final Function<T, R> mapper){
        final var aNewList = this.items.stream().map(mapper).toList();
        return new Pagination<>(currentPage(), perPage(), total(), aNewList);
    }
}
