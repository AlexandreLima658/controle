package com.alexandre.controle.gastos.domain.payment;

import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.attributes.PaymentId;

import java.time.LocalDate;

public interface PaymentFactory {


    static Payment create(
            final PaymentId paymentId,
            final LocalDate paymentDate,
            final ExpenseId expenseId
    ) {

        return new Payment(
                paymentId,
                paymentDate,
                expenseId
        );
    }


    static Payment create(
            final LocalDate paymentDate,
            final ExpenseId expenseId
    ) {

        final var paymentId = PaymentId.createWithNullValue();

        return new Payment(
                paymentId,
                paymentDate,
                expenseId
        );
    }
}
