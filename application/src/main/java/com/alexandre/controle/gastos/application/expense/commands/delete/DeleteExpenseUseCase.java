package com.alexandre.controle.gastos.application.expense.commands.delete;

import com.alexandre.controle.gastos.application.UnitUseCase;
import com.alexandre.controle.gastos.domain.expense.ExpenseRepository;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import jakarta.inject.Named;

@Named
public class DeleteExpenseUseCase extends UnitUseCase<Long> {

    private final ExpenseRepository repository;

    public DeleteExpenseUseCase(final ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final Long id) {
        final var expenseId = ExpenseId.from(id);
        this.repository.deleteById(expenseId);
    }
}
