package com.alexandre.controle.gastos.domain.category;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryDescription;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryName;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;

public class Category extends AggregateRoot<CategoryId> {

    private CategoryName name;
    private CategoryDescription description;

    Category(
            final CategoryId categoryId,
            final CategoryName name,
            final CategoryDescription description
    ) {
        super(categoryId);
        this.name = name;
        this.description = description;
    }

    public void update(
            String name,
            String description
    ) {
        this.name = new CategoryName(name);
        this.description = new CategoryDescription(description);
    }

    public CategoryName getName() {
        return name;
    }

    public CategoryDescription getDescription() {
        return description;
    }
}
