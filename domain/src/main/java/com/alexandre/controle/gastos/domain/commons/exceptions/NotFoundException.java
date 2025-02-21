package com.alexandre.controle.gastos.domain.commons.exceptions;

import com.alexandre.controle.gastos.domain.commons.attributes.Identifier;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;

public class NotFoundException extends DomainException {

    public NotFoundException(final ErrorInfo errorInfo) {
        super(errorInfo);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier<?> id
    ) {

        final var error = "'%s' with ID '%s' not found!"
                .formatted(anAggregate.getSimpleName(), id.value());

        return new NotFoundException(new ErrorInfo(error));
    }

}
