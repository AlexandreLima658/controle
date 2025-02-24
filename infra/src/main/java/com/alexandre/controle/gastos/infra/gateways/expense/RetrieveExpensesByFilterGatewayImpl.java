package com.alexandre.controle.gastos.infra.gateways.expense;


import com.alexandre.controle.gastos.application.expense.query.filter.RetrieveExpenseByFilterGateway;
import com.alexandre.controle.gastos.application.expense.query.filter.RetrieveExpensesByFilterInput;
import com.alexandre.controle.gastos.application.expense.query.filter.RetrieveExpensesByFilterOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.jpa.expense.ExpenseJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.expense.QExpenseJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Component
public class RetrieveExpensesByFilterGatewayImpl implements RetrieveExpenseByFilterGateway {

    private final EntityManager entityManager;
    private final QExpenseJpaEntity qExpenseJpa = QExpenseJpaEntity.expenseJpaEntity;

    public RetrieveExpensesByFilterGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Pagination<RetrieveExpensesByFilterOutput> execute(final RetrieveExpensesByFilterInput input) {

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(fromString(input.sortDirection()), input.sortBy())
        );

        var query = new JPAQuery<Void>(entityManager);

        final var expense = query.select(qExpenseJpa)
                .from(qExpenseJpa)
                .limit(page.getPageSize())
                .offset(page.getOffset())
                .fetch();

        query = new JPAQuery<>(entityManager);

        final var total = query.select(qExpenseJpa.count())
                .from(qExpenseJpa)
                .fetchOne();

        return new Pagination<>(
                input.page(),
                input.perPage(),
                Optional.ofNullable(total).orElse(0L),
                expense.stream().map(this::mapperFrom).toList()
        );
    }

    private RetrieveExpensesByFilterOutput mapperFrom(final ExpenseJpaEntity jpa) {

        return new RetrieveExpensesByFilterOutput(
                jpa.getId(),
                jpa.getUser().getId(),
                jpa.getCategory().getId(),
                jpa.getValue(),
                jpa.getDescription(),
                jpa.getStatus()
        );
    }
}
