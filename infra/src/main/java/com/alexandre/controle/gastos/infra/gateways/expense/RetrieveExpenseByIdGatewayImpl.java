package com.alexandre.controle.gastos.infra.gateways.expense;


import com.alexandre.controle.gastos.application.expense.query.id.RetrieveExpenseByIdGateway;
import com.alexandre.controle.gastos.application.expense.query.id.RetrieveExpenseByIdOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.infra.jpa.expense.ExpenseJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.expense.QExpenseJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RetrieveExpenseByIdGatewayImpl implements RetrieveExpenseByIdGateway {

    private final EntityManager entityManager;
    private final QExpenseJpaEntity qExpenseJpa = QExpenseJpaEntity.expenseJpaEntity;

    public RetrieveExpenseByIdGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public RetrieveExpenseByIdOutput execute(final Long id) {

        var query = new JPAQuery<Void>(entityManager);

        final var expense = query.select(qExpenseJpa)
                .from(qExpenseJpa)
                .where(qExpenseJpa.id.eq(id))
                .fetchOne();


        return Optional.ofNullable(expense)
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Expense.class, ExpenseId.from(id)));

    }

    private RetrieveExpenseByIdOutput mapperFrom(final ExpenseJpaEntity jpa) {

        return new RetrieveExpenseByIdOutput(
                jpa.getId(),
                jpa.getUser().getId(),
                jpa.getCategory().getId(),
                jpa.getValue(),
                jpa.getDescription(),
                jpa.getStatus()
        );
    }
}
