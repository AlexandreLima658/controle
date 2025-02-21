package com.alexandre.controle.gastos.application.category.commands.delete;

import com.alexandre.controle.gastos.application.UnitUseCase;
import com.alexandre.controle.gastos.domain.category.CategoryRepository;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import jakarta.inject.Named;

@Named
public class DeleteCategoryUseCase extends UnitUseCase<Long> {

    private final CategoryRepository repository;

    public DeleteCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final Long id) {

        final var categoryId = CategoryId.from(id);

        this.repository.deleteById(categoryId);
    }
}
