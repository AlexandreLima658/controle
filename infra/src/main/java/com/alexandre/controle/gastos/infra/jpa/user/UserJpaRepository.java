package com.alexandre.controle.gastos.infra.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long>, QuerydslPredicateExecutor<UserJpaEntity> {
    Optional<UserJpaEntity> findByEmailAndPassword(String email, String password);
}
