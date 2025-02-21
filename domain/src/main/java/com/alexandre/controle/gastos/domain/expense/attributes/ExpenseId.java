package com.alexandre.controle.gastos.domain.expense.attributes;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;

public class ExpenseId extends Identifier<Long> {

    public ExpenseId(Long value) {
        super(value);
    }

    public static ExpenseId from(final Long value) {
        return new ExpenseId(value);
    }

    public static ExpenseId createWithNullValue(){
        return new ExpenseId(null);
    }
}
