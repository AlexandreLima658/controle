package com.alexandre.controle.gastos.application.expense.commands.create;

import java.math.BigDecimal;

public record CreateExpenseInput(
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description
) {
}
