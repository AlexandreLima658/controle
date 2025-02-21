package com.alexandre.controle.gastos.infra.jpa.category;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Table(name = "categories")
@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoryJpaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
