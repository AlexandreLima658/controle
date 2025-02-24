package com.alexandre.controle.gastos.infra.rest.expense;


import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseInput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.create.CreateExpenseUseCase;
import com.alexandre.controle.gastos.application.expense.commands.delete.DeleteExpenseUseCase;
import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseOutput;
import com.alexandre.controle.gastos.application.expense.commands.update.UpdateExpenseUseCase;
import com.alexandre.controle.gastos.application.expense.query.filter.RetrieveExpensesByFilterInput;
import com.alexandre.controle.gastos.application.expense.query.filter.RetrieveExpensesByFilterOutput;
import com.alexandre.controle.gastos.application.expense.query.id.RetrieveExpenseByIdOutput;
import com.alexandre.controle.gastos.domain.pagination.Pagination;
import com.alexandre.controle.gastos.infra.gateways.expense.RetrieveExpenseByIdGatewayImpl;
import com.alexandre.controle.gastos.infra.gateways.expense.RetrieveExpensesByFilterGatewayImpl;
import com.alexandre.controle.gastos.infra.rest.expense.models.UpdateExpenseHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ExpenseController implements ExpenseAPI{

    private final CreateExpenseUseCase createExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;
    private final RetrieveExpensesByFilterGatewayImpl retrieveExpensesByFilterGateway;
    private final RetrieveExpenseByIdGatewayImpl retrieveExpenseByIdGateway;

    public ExpenseController(
            final CreateExpenseUseCase createExpenseUseCase,
            final UpdateExpenseUseCase updateExpenseUseCase,
            final DeleteExpenseUseCase deleteExpenseUseCase,
            final RetrieveExpensesByFilterGatewayImpl retrieveExpensesByFilterGateway,
            final RetrieveExpenseByIdGatewayImpl retrieveExpenseByIdGateway
    ) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
        this.retrieveExpensesByFilterGateway = retrieveExpensesByFilterGateway;
        this.retrieveExpenseByIdGateway = retrieveExpenseByIdGateway;
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

    @Override
    @Transactional
    public void delete(final Long expenseId) {
        this.deleteExpenseUseCase.execute(expenseId);
    }

    @Override
    @Transactional
    public ResponseEntity<Pagination<RetrieveExpensesByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {

        final var input = new RetrieveExpensesByFilterInput(
                page,
                perPage,
                sort,
                direction
        );

        return ResponseEntity.ok(this.retrieveExpensesByFilterGateway.execute(input));
    }

    @Override
    @Transactional
    public ResponseEntity<RetrieveExpenseByIdOutput> retrieveById(final Long expenseId) {
        return ResponseEntity.ok(this.retrieveExpenseByIdGateway.execute(expenseId));
    }
}
