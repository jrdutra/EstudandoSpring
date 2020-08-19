package com.jrdutra.clientes.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name="login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @Column(name="senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
