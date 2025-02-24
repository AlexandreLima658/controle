package com.alexandre.controle.gastos.domain.commons.attributes;

import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

public record Email(String value) {

    public Email {

        if (value != null) {

            if (value.trim().isBlank()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }

            if (!isValid(value)) {
                throw DomainException.with("Email is invalid");
            }
        }
    }

    public boolean isValid(final String value) {
        return value.matches("^[\\w.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
