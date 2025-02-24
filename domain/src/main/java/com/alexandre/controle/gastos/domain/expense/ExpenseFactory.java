package com.alexandre.controle.gastos.domain.expense;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.attributes.MonetaryValue;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExpenseFactory {

    static Expense create(
            final ExpenseId expenseId,
            final UserId userId,
            final CategoryId categoryId,
            final BigDecimal value,
            final String description,
            final LocalDate paymentDate
    ) {

        return new Expense(
                expenseId,
                userId,
                categoryId,
                new MonetaryValue(value),
                description,
                paymentDate
        );
    }

    static Expense create(
            final UserId userId,
            final CategoryId categoryId,
            final BigDecimal value,
            final String description,
            final LocalDate paymentDate

    ) {

        final var expenseId = ExpenseId.createWithNullValue();

        return new Expense(
                expenseId,
                userId,
                categoryId,
                new MonetaryValue(value),
                description,
                paymentDate

        );
    }
}
