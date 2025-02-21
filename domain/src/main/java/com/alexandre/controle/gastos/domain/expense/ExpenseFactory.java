package com.alexandre.controle.gastos.domain.expense;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

import java.math.BigDecimal;

public interface ExpenseFactory {

    static Expense create(
            final ExpenseId expenseId,
            final UserId userId,
            final CategoryId categoryId,
            final BigDecimal value,
            final String description,
            final Expense.PaymentStatus status
    ) {

        return new Expense(
                expenseId,
                userId,
                categoryId,
                value,
                description,
                status
        );
    }

    static Expense create(
            final UserId userId,
            final CategoryId categoryId,
            final BigDecimal value,
            final String description
    ) {

        final var expenseId = ExpenseId.createWithNullValue();

        return new Expense(
                expenseId,
                userId,
                categoryId,
                value,
                description,
                Expense.PaymentStatus.PENDING
        );
    }
}
