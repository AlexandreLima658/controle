package com.alexandre.controle.gastos.infra.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long>, QuerydslPredicateExecutor<CategoryJpaEntity> {
}
