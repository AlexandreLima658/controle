package com.alexandre.controle.gastos.infra.jpa.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ExpenseJpaRepository extends JpaRepository<ExpenseJpaEntity, Long>, QuerydslPredicateExecutor<ExpenseJpaEntity> {
}
