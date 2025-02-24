package com.alexandre.controle.gastos.application.expense.commands.update;


import com.alexandre.controle.gastos.domain.expense.Expense;

import java.math.BigDecimal;

public record UpdateExpenseInput(
        Long expenseId,
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description,
        Expense.PaymentStatus status
) {
}
