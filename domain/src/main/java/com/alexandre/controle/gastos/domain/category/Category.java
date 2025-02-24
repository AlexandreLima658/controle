package com.alexandre.controle.gastos.domain.category;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;

public class Category extends AggregateRoot<CategoryId> {

    private String name;
    private String description;

    Category(
            final CategoryId categoryId,
            final String name,
            final String description
    ) {
        super(categoryId);

        if (this.name.isEmpty()) {
            throw DomainException.with("Name is required");
        }

        if ( this.description.isEmpty()) {
            throw DomainException.with("Description is required");
        }
    }

    public void update(
            String name,
            String description
    ) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
