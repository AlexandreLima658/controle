package com.alexandre.controle.gastos.infra.gateways.user;

import com.alexandre.controle.gastos.domain.commons.attributes.Email;
import com.alexandre.controle.gastos.domain.user.User;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import com.alexandre.controle.gastos.domain.user.attributes.UserName;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaMapper;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    public UserRepositoryImpl(final UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(final UserId userId) {

        return repository.findById(userId.value())
                .map(UserJpaMapper::toAggregate);
    }

    @Override
    public UserId persist(final User user) {
        final var out = repository.save(UserJpaMapper.toJpaEntity(user));
        return UserId.from(out.getId());
    }

    @Override
    public void deleteById(final UserId userId) {
        this.repository.deleteById(userId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public Optional<User> findByEmailAndPassword(final Email email, final String password) {
        return repository.findByEmailAndPassword(email.value(), password)
                .map(UserJpaMapper::toAggregate);
    }
}
