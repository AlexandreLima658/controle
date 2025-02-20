package com.alexandre.controle.gastos.domain.commons.attributes;

import java.util.Objects;

public abstract class BaseEntity<Id extends Identifier<?>> {

    private final Id id;

    protected BaseEntity(final Id id) {
        this.id = id;
    }

    public Id id(){
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final var that = (BaseEntity<?>) other;
        return Objects.equals(id, that.id);
    }
}
