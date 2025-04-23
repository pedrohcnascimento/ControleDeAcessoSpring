package com.example.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Usuario {

    @Column(nullable = false)
    @JoinTable(
            name = "turmas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turma;

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gerador_id_acesso")
            @TableGenerator(
                    name = "gerador_id_acesso",
                    table = "id_generator",
                    pkColumnName = "idUsuario",
                    valueColumnName = "value_column",
                    pkColumnValue = "professor",
                    allocationSize = 1
            )
    Long idAcesso;

    @Column(nullable = false)
    private List<UnidadeCurricular> unidadesCurriculares;
}
