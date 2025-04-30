package com.senai.ControleDeAcessoSpring.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Usuario{
    @ManyToMany(mappedBy = "alunos")
    private List<SubTurma> subTurma;

    @OneToMany
    private List<Ocorrencia> ocorrencia;

    @OneToMany
    private List<Justificativa> justificativas;
}
