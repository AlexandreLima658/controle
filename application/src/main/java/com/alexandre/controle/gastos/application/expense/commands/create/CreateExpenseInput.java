package com.alexandre.controle.gastos.application.expense.commands.create;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseInput(
        Long userId,
        Long categoryId,
        BigDecimal value,
        String description,
        LocalDate paymentDate
) {
}
