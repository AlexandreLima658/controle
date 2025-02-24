package com.alexandre.controle.gastos.infra.rest.expense;

import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryInput;
import com.alexandre.controle.gastos.application.category.commands.create.CreateCategoryOutput;
import com.alexandre.controle.gastos.application.category.commands.update.UpdateCategoryOutput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseInput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.ErrorInfo;
import com.alexandre.controle.gastos.infra.rest.category.models.UpdateCategoryHttpRequest;
import com.alexandre.controle.gastos.infra.rest.expense.models.UpdateExpenseHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "expenses")
@Tag(name = "Expenses", description = "expenses")
public interface ExpenseAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateExpenseOutput> create(@RequestBody CreateExpenseInput input);

    @PutMapping(
            value = "{expenseId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a expense by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense updated successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<UpdateExpenseOutput> update(
            @PathVariable(name = "expenseId") Long expenseId,
            @RequestBody UpdateExpenseHttpRequest request
    );

    @DeleteMapping(value = "{expenseId}")
    @Operation(summary = "Deleted a expense by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    void delete(@PathVariable(name = "expenseId") final Long expenseId);


}
