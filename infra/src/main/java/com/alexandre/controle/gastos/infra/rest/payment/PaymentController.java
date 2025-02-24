package com.alexandre.controle.gastos.infra.rest.payment;


import com.alexandre.controle.gastos.application.payment.commands.create.CreatePaymentInput;
import com.alexandre.controle.gastos.application.payment.commands.create.CreatePaymentOutput;
import com.alexandre.controle.gastos.application.payment.commands.create.CreatePaymentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PaymentController implements PaymentAPI{

    private final CreatePaymentUseCase createPaymentUseCase;

    public PaymentController(final CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @Override
    @Transactional
    public ResponseEntity<CreatePaymentOutput> create(final CreatePaymentInput input) {

        final var payment = createPaymentUseCase.execute(input);
        final var uri = "/payments/" + payment.id();

        return ResponseEntity.created(URI.create(uri)).body(payment);
    }
}
