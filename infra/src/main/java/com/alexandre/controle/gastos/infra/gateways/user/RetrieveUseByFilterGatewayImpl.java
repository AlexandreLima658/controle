package com.alexandre.controle.gastos.infra.gateways.user;

import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUseByFilterGateway;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUseByFilterInput;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUserByFilterOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.jpa.user.QUserJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaEntity;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Component
public class RetrieveUseByFilterGatewayImpl implements RetrieveUseByFilterGateway {

    private final EntityManager entityManager;
    private QUserJpaEntity qUserJpa = QUserJpaEntity.userJpaEntity;

    public RetrieveUseByFilterGatewayImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination<RetrieveUserByFilterOutput> execute(final RetrieveUseByFilterInput input) {

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(fromString(input.sortDirection()), input.sortBy())
        );

        var query = new JPAQuery<Void>(entityManager);

        final var output = query.select(qUserJpa)
                .from(qUserJpa)
                .limit(page.getPageSize())
                .offset(page.getOffset())
                .fetch();

        query = new JPAQuery<>(entityManager);

        final var total = query.select(qUserJpa.count())
                .from(qUserJpa)
                .fetchOne();

        return new Pagination<>(
                input.page(),
                input.perPage(),
                Optional.ofNullable(total).orElse(0L),
                output.stream().map(this::mapperFrom).toList()
        );
    }

    private RetrieveUserByFilterOutput mapperFrom(final UserJpaEntity jpa) {
        return new RetrieveUserByFilterOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getEmail()
        );
    }
}
