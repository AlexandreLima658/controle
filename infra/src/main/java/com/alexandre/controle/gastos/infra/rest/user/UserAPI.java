package com.alexandre.controle.gastos.infra.rest.user;


import com.alexandre.controle.gastos.application.user.commands.create.CreateUserInput;
import com.alexandre.controle.gastos.application.user.commands.create.CreateUserOutput;
import com.alexandre.controle.gastos.application.user.commands.update.UpdateUserOutput;
import com.alexandre.controle.gastos.application.user.query.filter.RetrieveUserByFilterOutput;
import com.alexandre.controle.gastos.application.user.query.id.RetrieveUserByIdOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.ErrorInfo;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.rest.user.models.UpdateUserHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "users")
@Tag(name = "users")
public interface UserAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateUserOutput> create(@RequestBody CreateUserInput input);


    @GetMapping
    @Operation(summary = "Retrieve a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully recovered"),
            @ApiResponse(responseCode = "422", description = "Validation failed",content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<Pagination<RetrieveUserByFilterOutput>> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve user by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully recovered "),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<RetrieveUserByIdOutput> retrieveById(
            @PathVariable(name = "userId") Long userId
    );

    @PutMapping(
            value = "{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a user by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<UpdateUserOutput> update(
            @PathVariable(name = "userId") Long userId,
            @RequestBody UpdateUserHttpRequest request
    );

    @DeleteMapping(value = "{userId}")
    @Operation(summary = "Delete user by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    void delete(@PathVariable(name = "userId") Long userId);


}
