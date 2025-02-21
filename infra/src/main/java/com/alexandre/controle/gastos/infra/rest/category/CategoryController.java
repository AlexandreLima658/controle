package com.alexandre.controle.gastos.infra.rest.category;


import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryInput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryOutput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CategoryController implements CategoryAPI {

   private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @Override
    public ResponseEntity<CreateCategoryOutput> create(final CreateCategoryInput input) {

        final var out = createCategoryUseCase.execute(input);

        final var uri = "/categories/" + out.id();

        return ResponseEntity.created(URI.create(uri)).body(out);
    }
}
