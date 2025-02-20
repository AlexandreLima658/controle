package com.alexandre.controle.gastos.domain.user.attributes;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;

public class UserId extends Identifier<Long> {

    public UserId(Long value) {
        super(value);
    }

    public static UserId from(final Long value) {
        return new UserId(value);
    }

    public static UserId createWithNullValue(){
        return new UserId(null);
    }
}
