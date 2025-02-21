package com.alexandre.controle.gastos.infra.rest.category;


import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryInput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryOutput;
import com.alexandre.controle.gastos.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.ErrorInfo;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/categories")
@Tag(name = "categories", description = "categories")
public interface CategoryAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateCategoryOutput> create(@RequestBody CreateCategoryInput input);


    @GetMapping
    @Operation(summary = "Retrieve a list of categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories successfully recovered"),
            @ApiResponse(responseCode = "422", description = "Validation failed",content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<Pagination<RetrieveCategoriesByFilterOutput>> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );
}
