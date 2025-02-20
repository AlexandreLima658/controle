package com.alexandre.controle.gastos.domain.user;

import com.alexandre.controle.gastos.domain.user.attributes.UserId;

public interface UserFactory {

    static User create(
            final UserId userId,
            final String name,
            final String email,
            final String password
    ) {
        return new User(
                userId,
                name,
                email,
                password
        );
    }


    static User create(
            final String name,
            final String email,
            final String password
    ){
        final var userId = UserId.createWithNullValue();

        return new User(
                userId,
                name,
                email,
                password
        );
    }
}
