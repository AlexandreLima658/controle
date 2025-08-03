package com.alexandre.controle.gastos.domain.user;

import com.alexandre.controle.gastos.domain.Repository;
import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

import java.util.Optional;

public interface UserRepository extends Repository<User, UserId> {

     Optional<User> findByEmailAndPassword(Email email, String password);

}
