package com.alexandre.controle.gastos.application.payment.commands.create;


import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.PaymentFactory;
import com.alexandre.controle.gastos.domain.payment.PaymentRepository;
import jakarta.inject.Named;

@Named
public class CreatePaymentUseCase extends UseCase<CreatePaymentInput, CreatePaymentOutput> {

    private final PaymentRepository repository;

    public CreatePaymentUseCase(final PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreatePaymentOutput execute(final CreatePaymentInput input) {

        final var expenseId = ExpenseId.from(input.expenseId());

        final var paymentExists = repository.findByExpenseId(expenseId);

        if (paymentExists.isPresent()) {
            throw DomainException.with("Payment made for this expense");
        }

        final var payment = PaymentFactory.create(
                input.paymentDate(),
                expenseId
        );

        final var paymentId = this.repository.persist(payment);

        return new CreatePaymentOutput(paymentId.value());
    }
}
