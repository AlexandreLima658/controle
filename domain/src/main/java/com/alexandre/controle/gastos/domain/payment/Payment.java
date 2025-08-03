package com.alexandre.controle.gastos.domain.payment;

import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.attributes.PaymentId;

import java.time.LocalDate;

public class Payment extends AggregateRoot<PaymentId> {

    private final LocalDate paymentDate;
    private final ExpenseId expenseId;

    Payment(
            final PaymentId paymentId,
            final LocalDate paymentDate,
            final ExpenseId expenseId
    ) {
        super(paymentId);
        this.paymentDate = paymentDate;
        this.expenseId = expenseId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public ExpenseId getExpenseId() {
        return expenseId;
    }
}
