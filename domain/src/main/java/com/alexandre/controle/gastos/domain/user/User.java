package com.alexandre.controle.gastos.domain.user;

import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import com.alexandre.controle.gastos.domain.user.attributes.UserName;

public class User extends AggregateRoot<UserId> {

    private UserName name;
    private Email email;
    private String password;
    private UserRole role;

    public User(
            final UserId userId,
            final UserName name,
            final Email email,
            final String password,
            final UserRole role
    ) {
       super(userId);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void update(
            String name,
            String email,
            String password
    ){
        this.name = new UserName(name);
        this.email = new Email(email);
        this.password = password;
    }

    public UserName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public enum UserRole {
        ADMIN("admin"),
        USER("user");

        private final String role;

        UserRole(final String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
}
