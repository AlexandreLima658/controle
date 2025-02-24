package com.alexandre.controle.gastos.application.expense.query.id;

import com.alexandre.controle.gastos.domain.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RetrieveExpenseByIdOutput(
        Long expenseId,
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description,
        LocalDate paymentDate
) {
}
