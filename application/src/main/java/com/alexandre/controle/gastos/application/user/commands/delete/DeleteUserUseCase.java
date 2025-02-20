package com.alexandre.controle.gastos.application.user.commands.delete;


import com.alexandre.controle.gastos.application.UnitUseCase;
import com.alexandre.controle.gastos.domain.user.UserRepository;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import jakarta.inject.Named;

@Named
public class DeleteUserUseCase extends UnitUseCase<Long> {

    private final UserRepository repository;

    public DeleteUserUseCase(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final Long id) {

        final var userId = UserId.from(id);

        this.repository.deleteById(userId);
    }
}
