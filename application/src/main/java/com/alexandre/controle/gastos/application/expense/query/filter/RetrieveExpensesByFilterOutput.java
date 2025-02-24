package com.alexandre.controle.gastos.application.expense.query.filter;

import com.alexandre.controle.gastos.domain.expense.Expense;

import java.math.BigDecimal;

public record RetrieveExpensesByFilterOutput(
        Long expenseId,
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description,
        Expense.PaymentStatus status
) {
}
