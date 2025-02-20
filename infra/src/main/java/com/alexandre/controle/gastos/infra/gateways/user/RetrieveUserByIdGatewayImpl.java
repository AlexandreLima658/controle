package com.alexandre.controle.gastos.infra.gateways.user;


import com.alexandre.controle.gastos.application.user.query.id.RetrieveUserByIdGateway;
import com.alexandre.controle.gastos.application.user.query.id.RetrieveUserByIdOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import com.alexandre.controle.gastos.domain.user.User;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import com.alexandre.controle.gastos.infra.jpa.user.QUserJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class RetrieveUserByIdGatewayImpl implements RetrieveUserByIdGateway {

    private final EntityManager entityManager;
    private final QUserJpaEntity qUserJpa = QUserJpaEntity.userJpaEntity;

    public RetrieveUserByIdGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public RetrieveUserByIdOutput execute(final Long id) {

        var query = new JPAQuery<Void>(entityManager);

        final var out = query.select(qUserJpa)
                .from(qUserJpa)
                .where(qUserJpa.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(out)
                .map(this::mapperFrom).orElseThrow(() -> NotFoundException.with(User.class, UserId.from(id)));
    }

    private RetrieveUserByIdOutput mapperFrom(final UserJpaEntity jpa) {

        return new RetrieveUserByIdOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getEmail()
        );
    }
}
