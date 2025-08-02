package com.alexandre.controle.gastos.domain.category.attributes;

import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

public record CategoryDescription(String value) {

    public CategoryDescription {

        if (value == null || value.isEmpty()) {
            throw DomainException.with("Name cannot be null or empty");
        }
    }
}
