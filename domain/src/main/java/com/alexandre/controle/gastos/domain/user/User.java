package com.alexandre.controle.gastos.domain.user;

import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

public class User extends AggregateRoot<UserId> {

    private final String name;
    private final String email;
    private final String password;

    public User(
            final UserId userId,
            final String name,
            final String email,
            final String password
    ) {
       super(userId);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
