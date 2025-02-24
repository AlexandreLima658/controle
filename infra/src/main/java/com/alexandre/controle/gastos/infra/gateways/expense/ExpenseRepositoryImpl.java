package com.alexandre.controle.gastos.infra.gateways.expense;


import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.ExpenseRepository;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.infra.jpa.expense.ExpenseJpaMapper;
import com.alexandre.controle.gastos.infra.jpa.expense.ExpenseJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final ExpenseJpaRepository repository;

    public ExpenseRepositoryImpl(final ExpenseJpaRepository expenseJpaRepository) {
        this.repository = expenseJpaRepository;
    }

    @Override
    public Optional<Expense> findById(final ExpenseId expenseId) {
        return repository.findById(expenseId.value())
                .map(ExpenseJpaMapper::toAggregate);

    }

    @Override
    public ExpenseId persist(final Expense aggregate) {

        final var expense = repository.save(ExpenseJpaMapper.toJpaEntity(aggregate));

        return ExpenseId.from(expense.getId());
    }

    @Override
    public void deleteById(final ExpenseId expenseId) {
        this.repository.deleteById(expenseId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
