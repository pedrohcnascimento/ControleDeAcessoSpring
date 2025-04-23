package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Usuario{

    @Column
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column
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
