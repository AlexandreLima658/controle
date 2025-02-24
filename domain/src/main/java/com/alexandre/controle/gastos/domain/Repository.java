package com.alexandre.controle.gastos.domain;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;

import java.util.Optional;

public interface Repository<T extends AggregateRoot<?>, ID extends Identifier<?>> {

    Optional<T> findById(final ID id);

    ID persist(final T aggregate);

    void deleteById(final ID id);

    void deleteAll();
}
