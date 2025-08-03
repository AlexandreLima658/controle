package com.alexandre.controle.gastos.infra.gateways.payment;


import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.Payment;
import com.alexandre.controle.gastos.domain.payment.PaymentRepository;
import com.alexandre.controle.gastos.domain.payment.attributes.PaymentId;
import com.alexandre.controle.gastos.infra.jpa.payment.PaymentJpaMapper;
import com.alexandre.controle.gastos.infra.jpa.payment.PaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository repository;

    public PaymentRepositoryImpl(PaymentJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Payment> findById(final PaymentId paymentId) {
        return repository.findById(paymentId.value())
                .map(PaymentJpaMapper::toAggregate);
    }

    @Override
    public PaymentId persist(final Payment aggregate) {
        final var payment = repository.save(PaymentJpaMapper.toJpaEntity(aggregate));
        return PaymentId.from(payment.getId());
    }

    @Override
    public void deleteById(PaymentId paymentId) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<Payment> findByExpenseId(final ExpenseId expenseId) {
        return repository.findByExpenseId(expenseId.value())
                .map(PaymentJpaMapper::toAggregate);
    }
}
