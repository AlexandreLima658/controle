package com.alexandre.controle.gastos.domain.category;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryDescription;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryName;

public interface CategoryFactory {

    static Category create(
            final CategoryId categoryId,
            final CategoryName name,
            final CategoryDescription description
    ) {

        return new Category(
                categoryId,
                name,
                description
        );
    }

    static Category create(
            final CategoryName name,
            final CategoryDescription description
    ){
        final var categoryId = CategoryId.createWithNullValue();

        return new Category(
                categoryId,
                name,
                description
        );

    }
}
