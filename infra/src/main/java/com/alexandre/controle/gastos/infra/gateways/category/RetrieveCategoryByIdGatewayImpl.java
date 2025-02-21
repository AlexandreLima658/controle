package com.alexandre.controle.gastos.infra.gateways.category;


import com.alexandre.controle.gastos.application.category.query.id.RetrieveCategoryByIdGateway;
import com.alexandre.controle.gastos.application.category.query.id.RetrieveCategoryByIdOutput;
import com.alexandre.controle.gastos.domain.category.Category;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.category.QCategoryJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RetrieveCategoryByIdGatewayImpl implements RetrieveCategoryByIdGateway {

    private final EntityManager entityManager;
    private final QCategoryJpaEntity qCategoryJpa = QCategoryJpaEntity.categoryJpaEntity;

    public RetrieveCategoryByIdGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RetrieveCategoryByIdOutput execute(final Long id) {

        var query = new JPAQuery<Void>(entityManager);

        final var category = query.select(qCategoryJpa)
                .from(qCategoryJpa)
                .where(qCategoryJpa.id.eq(id))
                .fetchOne();


        return Optional.ofNullable(category)
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Category.class, CategoryId.from(id)));
    }

    private RetrieveCategoryByIdOutput mapperFrom(final CategoryJpaEntity jpa) {

        return new RetrieveCategoryByIdOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription()
        );
    }
}
