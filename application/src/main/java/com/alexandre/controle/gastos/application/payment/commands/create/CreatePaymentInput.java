package com.alexandre.controle.gastos.application.payment.commands.create;

import java.time.LocalDate;

public record CreatePaymentInput(
        LocalDate paymentDate,
        Long expenseId
) {
}
