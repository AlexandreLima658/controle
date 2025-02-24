package com.alexandre.controle.gastos.domain.user.attributes;

import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

public record UserName(String value) {

    public UserName {

        if (value == null || value.isBlank()) {
            throw DomainException.with("UserName cannot be null or empty");
        }
    }
}
