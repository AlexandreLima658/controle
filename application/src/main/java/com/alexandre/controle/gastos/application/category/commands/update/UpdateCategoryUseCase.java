package com.alexandre.controle.gastos.application.category.commands.update;

import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.category.Category;
import com.alexandre.controle.gastos.domain.category.CategoryRepository;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

@Named
public class UpdateCategoryUseCase extends UseCase<UpdateCategoryInput, UpdateCategoryOutput> {

    private final CategoryRepository repository;

    public UpdateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateCategoryOutput execute(final UpdateCategoryInput input) {

        final var categoryId = CategoryId.from(input.id());

        final var category = repository.findById(categoryId)
                .orElseThrow(() -> NotFoundException.with(Category.class, categoryId));

        category.update(
                input.name(),
                input.description()
        );

        final var id = this.repository.persist(category);

        return new UpdateCategoryOutput(id.value());
    }
}
