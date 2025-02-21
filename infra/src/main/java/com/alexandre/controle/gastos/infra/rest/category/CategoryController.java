package com.alexandre.controle.gastos.infra.rest.category;


import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryInput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryOutput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryUseCase;
import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterInput;
import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.alexandre.controle.gastos.application.category.query.id.RetrieveCategoryByIdOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.gateways.category.RetrieveCategoriesByFilterGatewayImpl;
import com.alexandre.controle.gastos.infra.gateways.category.RetrieveCategoryByIdGatewayImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final RetrieveCategoriesByFilterGatewayImpl retrieveCategoriesByFilterGateway;
    private final RetrieveCategoryByIdGatewayImpl retrieveCategoryByIdGateway;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final RetrieveCategoriesByFilterGatewayImpl retrieveCategoriesByFilterGateway,
            final RetrieveCategoryByIdGatewayImpl retrieveCategoryByIdGateway
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.retrieveCategoriesByFilterGateway = retrieveCategoriesByFilterGateway;
        this.retrieveCategoryByIdGateway = retrieveCategoryByIdGateway;
    }

    @Override
    public ResponseEntity<CreateCategoryOutput> create(final CreateCategoryInput input) {

        final var out = createCategoryUseCase.execute(input);

        final var uri = "/categories/" + out.id();

        return ResponseEntity.created(URI.create(uri)).body(out);
    }

    @Override
    public ResponseEntity<Pagination<RetrieveCategoriesByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {

        final var input = new RetrieveCategoriesByFilterInput(
                page,
                perPage,
                sort,
                direction
        );

        return ResponseEntity.ok(this.retrieveCategoriesByFilterGateway.execute(input));
    }

    @Override
    @Transactional
    public ResponseEntity<RetrieveCategoryByIdOutput> retrieveById(final Long userId) {

        return ResponseEntity.ok(this.retrieveCategoryByIdGateway.execute(userId));
    }


}
