package com.alexandre.controle.gastos.domain.commons.entites;

import com.alexandre.controle.gastos.domain.commons.attributes.BaseEntity;
import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;

public abstract class AggregateRoot<Id extends Identifier<?>> extends BaseEntity<Id> {

    protected AggregateRoot(Id id) {
        super(id);
    }
}
