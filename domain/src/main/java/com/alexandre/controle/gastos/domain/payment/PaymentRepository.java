package com.alexandre.controle.gastos.domain.payment;

import com.alexandre.controle.gastos.domain.Repository;
import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.payment.attributes.PaymentId;

import java.util.Optional;

public interface PaymentRepository extends Repository<Payment, PaymentId> {

    Optional<Payment> findByExpenseId(ExpenseId expenseId);
}
