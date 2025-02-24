package com.alexandre.controle.gastos.infra.rest.payment;


import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseInput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseOutput;
import com.alexandre.controle.gastos.application.payment.commands.create.CreatePaymentInput;
import com.alexandre.controle.gastos.application.payment.commands.create.CreatePaymentOutput;
import com.alexandre.controle.gastos.domain.commons.exceptions.ErrorInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "payments")
@Tag(name = "Payments", description = "payments")
public interface PaymentAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreatePaymentOutput> create(@RequestBody CreatePaymentInput input);
}
