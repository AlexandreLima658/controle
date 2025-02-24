package com.alexandre.controle.gastos.infra.rest.expense;


import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseInput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseUseCase;
import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseUseCase;
import com.alexandre.controle.gastos.infra.rest.expense.models.UpdateExpenseHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ExpenseController implements ExpenseAPI{

    private final CreateExpenseUseCase createExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;

    public ExpenseController(
            final CreateExpenseUseCase createExpenseUseCase,
            final UpdateExpenseUseCase updateExpenseUseCase
    ) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
    }

    @Override
    @Transactional
    public ResponseEntity<CreateExpenseOutput> create(final CreateExpenseInput input) {

        final var expense = createExpenseUseCase.execute(input);

        final var uri = "/expenses/" + expense.id();

        return ResponseEntity.created(URI.create(uri)).body(expense);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateExpenseOutput> update(final Long expenseId, final UpdateExpenseHttpRequest request) {

        final var updateExpense = request.toInput(expenseId);

        return ResponseEntity.ok(this.updateExpenseUseCase.execute(updateExpense));
    }
}
