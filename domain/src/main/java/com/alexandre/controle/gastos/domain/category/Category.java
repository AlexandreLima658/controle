package com.alexandre.controle.gastos.domain.category;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;

public class Category extends AggregateRoot<CategoryId> {

    private String name;
    private String description;

    Category(
            final CategoryId categoryId,
            final String name,
            final String description
    ) {
        super(categoryId);
        this.name = name;
        this.description = description;
    }

    public void update(
            String name,
            String description
    ){
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
