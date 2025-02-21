package com.alexandre.controle.gastos.infra.rest.expense;


import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseInput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ExpenseController implements ExpenseAPI{

    private final CreateExpenseUseCase createExpenseUseCase;

    public ExpenseController(final CreateExpenseUseCase expenseUseCase) {
        this.createExpenseUseCase = expenseUseCase;
    }

    @Override
    @Transactional
    public ResponseEntity<CreateExpenseOutput> create(final CreateExpenseInput input) {

        final var expense = createExpenseUseCase.execute(input);

        final var uri = "/expenses/" + expense.id();

        return ResponseEntity.created(URI.create(uri)).body(expense);
    }
}
