package com.alexandre.controle.gastos.domain.category.attributes;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;

public class CategoryId extends Identifier<Long> {

    public CategoryId(final Long value) {
        super(value);
    }

    public static CategoryId from(final Long value) {
        return new CategoryId(value);
    }

    public static CategoryId createWithNullValue() {
        return new CategoryId(null);
    }


}
