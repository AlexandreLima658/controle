package com.alexandre.controle.gastos.infra.gateways.category;


import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterGateway;
import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterInput;
import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.category.QCategoryJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Component
public class RetrieveCategoriesByFilterGatewayImpl implements RetrieveCategoriesByFilterGateway {

    private final EntityManager entityManager;
    private final QCategoryJpaEntity qCategoryJpaEntity = QCategoryJpaEntity.categoryJpaEntity;

    public RetrieveCategoriesByFilterGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Pagination<RetrieveCategoriesByFilterOutput> execute(final RetrieveCategoriesByFilterInput input) {

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(fromString(input.sortDirection()), input.sortBy())
        );

        var query = new JPAQuery<Void>(entityManager);

        final var category = query.select(qCategoryJpaEntity)
                .from(qCategoryJpaEntity)
                .fetch();

        query = new JPAQuery<>(entityManager);

        final var total = query.select(qCategoryJpaEntity.count())
                .from(qCategoryJpaEntity)
                .fetchOne();

        return new Pagination<>(
                input.page(),
                input.perPage(),
                Optional.ofNullable(total).orElse(0L),
                category.stream().map(this::mapperFrom).toList()
        );
    }


    private RetrieveCategoriesByFilterOutput mapperFrom(final CategoryJpaEntity jpa) {

        return new RetrieveCategoriesByFilterOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription()
        );
    }
}
