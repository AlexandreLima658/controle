package com.alexandre.controle.gastos.infra.config;


import com.alexandre.controle.gastos.infra.jpa.user.UserJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class Authorization implements UserDetailsService {

    private final UserJpaRepository repository;

    public Authorization(final UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return repository.findByName(username);
    }

}
