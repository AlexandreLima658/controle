package com.alexandre.controle.gastos.domain.user;

import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import com.alexandre.controle.gastos.domain.user.attributes.UserName;

public interface UserFactory {

    static User create(
            final UserId userId,
            final String name,
            final String email,
            final String password
    ) {
        return new User(
                userId,
                new UserName(name),
                new Email(email),
                password
        );
    }


    static User create(
            final UserName name,
            final Email email,
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
