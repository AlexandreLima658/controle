package com.alexandre.controle.gastos.infra.jpa.category;

import com.alexandre.controle.gastos.domain.category.Category;
import com.alexandre.controle.gastos.domain.category.CategoryFactory;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;

public interface CategoryJpaMapper {

    static CategoryJpaEntity toJpaEntity(final Category category) {

        return new CategoryJpaEntity(
                category.id().value(),
                category.getName(),
                category.getDescription()
        );
    }

    static Category toAggregate(final CategoryJpaEntity jpa) {

        final var categoryId = CategoryId.from(jpa.getId());

        return CategoryFactory.create(
                categoryId,
                jpa.getName(),
                jpa.getDescription()
        );
    }
}
