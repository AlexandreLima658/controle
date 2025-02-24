package com.alexandre.controle.gastos.infra.jpa.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long>, QuerydslPredicateExecutor<PaymentJpaEntity> {

    Optional<PaymentJpaEntity> findByExpenseId(final Long id);
}
