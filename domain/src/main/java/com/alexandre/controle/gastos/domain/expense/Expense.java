package com.alexandre.controle.gastos.domain.expense;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.attributes.MonetaryValue;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense extends AggregateRoot<ExpenseId> {

    private UserId userId;
    private CategoryId categoryId;
    private MonetaryValue value;
    private String description;
    private LocalDate paymentDate;

    Expense(
            final ExpenseId expenseId,
            final UserId userId,
            final CategoryId categoryId,
            final MonetaryValue value,
            final String description,
            final LocalDate paymentDate
    ) {
        super(expenseId);
        this.userId = userId;
        this.categoryId = categoryId;
        this.value = value;
        this.description = description;
        this.paymentDate = paymentDate;
    }

    public void update(
        final Long userId,
        final Long categoryId,
        final BigDecimal value,
        final String description,
        final LocalDate paymentDate
    ) {
        this.userId = UserId.from(userId);
        this.categoryId = CategoryId.from(categoryId);
        this.value = new MonetaryValue(value);
        this.description = description;
        this.paymentDate = paymentDate;

    }

    public UserId getUserId() {
        return userId;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public MonetaryValue getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

}
