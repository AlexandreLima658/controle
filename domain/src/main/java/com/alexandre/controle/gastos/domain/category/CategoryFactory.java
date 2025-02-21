package com.alexandre.controle.gastos.domain.category;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;

public interface CategoryFactory {

    static Category create(
            final CategoryId categoryId,
            final String name,
            final String description
    ) {

        return new Category(
                categoryId,
                name,
                description
        );
    }

    static Category create(
            final String name,
            final String description
    ){
        final var categoryId = CategoryId.createWithNullValue();

        return new Category(
                categoryId,
                name,
                description
        );

    }
}
