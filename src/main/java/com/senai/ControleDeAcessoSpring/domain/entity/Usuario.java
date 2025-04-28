package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gerador_id_acesso")
    @TableGenerator(
            name = "gerador_id_acesso",
            table = "id_generator",
            pkColumnName = "idUsuario",
            valueColumnName = "value_column",
            pkColumnValue = "aluno",
            allocationSize = 1
    )
    private Long idAcesso;
}
