package com.alexandre.controle.gastos.infra.jpa.user;


import com.alexandre.controle.gastos.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}



