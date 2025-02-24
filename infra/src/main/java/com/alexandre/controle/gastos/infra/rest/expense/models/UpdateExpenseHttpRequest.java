package com.alexandre.controle.gastos.infra.rest.expense.models;

import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseInput;
import com.alexandre.controle.gastos.domain.expense.Expense;

import java.math.BigDecimal;

public record UpdateExpenseHttpRequest(
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description,
        Expense.PaymentStatus status
) {

    public UpdateExpenseInput toInput(final Long expenseId) {

        return new UpdateExpenseInput(
                expenseId,
                userId,
                categoryId,
                value,
                description,
                status
        );
    }
}
