package com.alexandre.controle.gastos.infra.jpa.category;

import com.alexandre.controle.gastos.domain.category.Category;
import com.alexandre.controle.gastos.domain.category.CategoryFactory;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryDescription;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryName;

public interface CategoryJpaMapper {

    static CategoryJpaEntity toJpaEntity(final Category category) {

        return new CategoryJpaEntity(
                category.id().value(),
                category.getName().value(),
                category.getDescription().value()
        );
    }

    static Category toAggregate(final CategoryJpaEntity jpa) {

        final var categoryId = CategoryId.from(jpa.getId());

        return CategoryFactory.create(
                categoryId,
                new CategoryName(jpa.getName()),
                new CategoryDescription(jpa.getDescription())
        );
    }
}
