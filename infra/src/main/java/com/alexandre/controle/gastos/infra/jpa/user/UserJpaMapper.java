package com.alexandre.controle.gastos.infra.jpa.user;


import com.alexandre.controle.gastos.domain.user.User;
import com.alexandre.controle.gastos.domain.user.UserFactory;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

public interface UserJpaMapper {

    static UserJpaEntity toJpaEntity(final User user) {

        return new UserJpaEntity(
                user.id().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getPassword()

        );
    }

    static User toAggregate(final UserJpaEntity jpa) {

        final var userId = UserId.from(jpa.getId());

        return UserFactory.create(
                userId,
                jpa.getName(),
                jpa.getEmail(),
                jpa.getPassword()
        );
    }
}
