package com.alexandre.controle.gastos.infra.gateways.category;

import com.alexandre.controle.gastos.domain.category.Category;
import com.alexandre.controle.gastos.domain.category.CategoryRepository;
import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaMapper;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository repository;

    public CategoryRepositoryImpl(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> findById(final CategoryId categoryId) {
        return repository.findById(categoryId.value())
                .map(CategoryJpaMapper::toAggregate);
    }

    @Override
    public CategoryId persist(final Category aggregate) {
        final var category = repository.save(CategoryJpaMapper.toJpaEntity(aggregate));
        return CategoryId.from(category.getId());
    }

    @Override
    public void deleteById(final CategoryId categoryId) {
        this.repository.findById(categoryId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
