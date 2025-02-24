package com.alexandre.controle.gastos.application.expense.commands.create;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.expense.ExpenseFactory;
import com.alexandre.controle.gastos.domain.expense.ExpenseRepository;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import jakarta.inject.Named;

@Named
public class CreateExpenseUseCase extends UseCase<CreateExpenseInput, CreateExpenseOutput> {

    private final ExpenseRepository repository;

    public CreateExpenseUseCase(final ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateExpenseOutput execute(final CreateExpenseInput input) {

        final var userId = UserId.from(input.userId());
        final var categoryId = CategoryId.from(input.categoryId());

        final var expense = ExpenseFactory.create(
                userId,
                categoryId,
                input.value(),
                input.description(),
                input.paymentDate()

        );

        final var expenseId = this.repository.persist(expense);

        return new CreateExpenseOutput(expenseId.value());
    }
}
