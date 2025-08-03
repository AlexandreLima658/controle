package com.alexandre.controle.gastos.infra.jpa.payment;

import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.Payment;
import com.alexandre.controle.gastos.domain.payment.PaymentFactory;
import com.alexandre.controle.gastos.domain.payment.attributes.PaymentId;
import com.alexandre.controle.gastos.infra.jpa.expense.ExpenseJpaEntity;

public interface PaymentJpaMapper {

    static PaymentJpaEntity toJpaEntity(final Payment payment) {

        final var expenseEntity = new ExpenseJpaEntity()
                .setId(payment.getExpenseId().value());

        return new PaymentJpaEntity(
                payment.id().value(),
                payment.getPaymentDate(),
                expenseEntity
        );
    }

    static Payment toAggregate(final PaymentJpaEntity jpa) {

        final var paymentId = PaymentId.from(jpa.getId());
        final var expenseId = ExpenseId.from(jpa.getId());

        return PaymentFactory.create(
                paymentId,
                jpa.getPaymentDate(),
                expenseId
        );
    }
}
