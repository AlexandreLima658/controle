package com.alexandre.controle.gastos.infra.jpa.expense;

import com.alexandre.controle.gastos.domain.category.attributes.CategoryId;
import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.domain.expense.ExpenseFactory;
import com.alexandre.controle.gastos.domain.expense.attributes.ExpenseId;
import com.alexandre.controle.gastos.domain.user.attributes.UserId;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaEntity;

public interface ExpenseJpaMapper {

    static ExpenseJpaEntity toJpaEntity(final Expense expense) {

        final var user = new UserJpaEntity()
                .setId(expense.getUserId().value());

        final var category = new CategoryJpaEntity()
                .setId(expense.getCategoryId().value());

        return new ExpenseJpaEntity(
                expense.id().value(),
                expense.getValue().value(),
                expense.getDescription(),
                expense.getPaymentDate(),
                user,
                category

        );
    }

    static Expense toAggregate(final ExpenseJpaEntity jpa) {

        final var expenseId = ExpenseId.from(jpa.getId());
        final var userId = UserId.from(jpa.getUser().getId());
        final var categoryId = CategoryId.from(jpa.getCategory().getId());


        return ExpenseFactory.create(
                expenseId,
                userId,
                categoryId,
                jpa.getValue(),
                jpa.getDescription(),
                jpa.getPaymentDate()

        );
    }

}
