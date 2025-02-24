package com.alexandre.controle.gastos.domain.commons.attributes;

import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

import java.math.BigDecimal;

public record MonetaryValue(BigDecimal value) {

    public MonetaryValue {

        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw DomainException.with("Unable to create an expense with a negative value");
        }
    }
}
