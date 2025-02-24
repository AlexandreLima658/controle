package com.alexandre.controle.gastos.infra.jpa.expense;


import com.alexandre.controle.gastos.domain.expense.Expense;
import com.alexandre.controle.gastos.infra.jpa.category.CategoryJpaEntity;
import com.alexandre.controle.gastos.infra.jpa.user.UserJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class ExpenseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value_expense")
    private BigDecimal value;

    @Column(name = "description")
    private String description;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpaEntity category;

}
