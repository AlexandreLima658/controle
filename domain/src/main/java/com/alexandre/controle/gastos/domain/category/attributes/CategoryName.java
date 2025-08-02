package com.alexandre.controle.gastos.domain.category.attributes;

import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

public record CategoryName(String value) {

    public CategoryName {

        if (value == null || value.isEmpty()) {
            throw DomainException.with("Name cannot be null or empty");
        }
    }
}
