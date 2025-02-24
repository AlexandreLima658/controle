package com.alexandre.controle.gastos.application.expense.commands.update;


import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.ExpenseRepository;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import jakarta.inject.Named;

@Named
public class UpdateExpenseUseCase extends UseCase<UpdateExpenseInput, UpdateExpenseOutput> {

    private final ExpenseRepository repository;

    public UpdateExpenseUseCase(final ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateExpenseOutput execute(final UpdateExpenseInput input) {

        final var expenseId = ExpenseId.from(input.expenseId());

        final var updatedExpense = repository.findById(expenseId)
                .orElseThrow(() -> NotFoundException.with(Expense.class, expenseId));

        updatedExpense.update(
                input.userId(),
                input.categoryId(),
                input.value(),
                input.description(),
                input.paymentDate()
        );

        final var id = repository.persist(updatedExpense);

        return new UpdateExpenseOutput(id.value());
    }
}
