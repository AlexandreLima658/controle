package com.alexandre.controle.gastos.application.category.commands.create;


import com.alexandre.controle.gastos.application.UseCase;
import com.alexandre.controle.gastos.domain.category.CategoryFactory;
import com.alexandre.controle.gastos.domain.category.CategoryRepository;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryDescription;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryName;
import jakarta.inject.Named;

@Named
public class CreateCategoryUseCase extends UseCase<CreateCategoryInput, CreateCategoryOutput> {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryInput input) {

        final var category = CategoryFactory.create(
                new CategoryName(input.name()),
                new CategoryDescription(input.description())
        );

        final var categoryId = this.repository.persist(category);

        return new CreateCategoryOutput(categoryId.value());
    }
}
