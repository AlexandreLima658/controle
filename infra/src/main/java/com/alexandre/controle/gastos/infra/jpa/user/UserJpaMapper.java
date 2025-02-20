package com.alexandre.controle.gastos.infra.jpa.user;


import com.alexandre.controle.gastos.domain.user.User;
import com.alexandre.controle.gastos.domain.user.UserFactory;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

public interface UserJpaMapper {

    static UserJpaEntity toJpaEntity(final User user) {

        return new UserJpaEntity(
                user.id().value(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }


    static User toAggregate(final UserJpaEntity jpa) {

        return UserFactory.create(
                UserId.from(jpa.getId()),
                jpa.getName(),
                jpa.getEmail(),
                jpa.getPassword()
        );
    }
}
