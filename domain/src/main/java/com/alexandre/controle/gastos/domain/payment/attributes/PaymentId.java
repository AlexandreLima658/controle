package com.alexandre.controle.gastos.domain.payment.attributes;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;

public class PaymentId extends Identifier<Long> {

    private PaymentId(final Long value) {
        super(value);
    }

    public static PaymentId from (final Long value) {
        return new PaymentId(value);
    }

    public static PaymentId createWithNullValue() {
        return new PaymentId(null);
    }
}
