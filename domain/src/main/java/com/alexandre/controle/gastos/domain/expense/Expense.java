package com.alexandre.controle.gastos.domain.expense;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.commons.entites.AggregateRoot;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;

import java.math.BigDecimal;

public class Expense extends AggregateRoot<ExpenseId> {

    private UserId userId;
    private CategoryId categoryId;
    private BigDecimal value;
    private String description;
    private PaymentStatus status;

    Expense(
            final ExpenseId expenseId,
            final UserId userId,
            final CategoryId categoryId,
            final BigDecimal value,
            final String description,
            final PaymentStatus status
    ) {
        super(expenseId);
        this.userId = userId;
        this.categoryId = categoryId;
        this.value = value;
        this.description = description;
        this.status = status;
    }

    public void update(
        final Long userId,
        final Long categoryId,
        final BigDecimal value,
        final String description,
        final PaymentStatus status
    ) {
        this.userId = UserId.from(userId);
        this.categoryId = CategoryId.from(categoryId);
        this.value = value;
        this.description = description;
        this.status = status;

    }

    public UserId getUserId() {
        return userId;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public enum PaymentStatus {
        PAID,
        PENDING,
        LATE
    }
}
