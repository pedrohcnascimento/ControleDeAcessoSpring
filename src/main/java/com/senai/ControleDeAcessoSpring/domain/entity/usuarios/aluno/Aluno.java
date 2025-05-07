package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("aluno")
public class Aluno extends Usuario {
    @ManyToMany(mappedBy = "alunos")
    private List<SubTurma> subTurma;

    @OneToMany
    private List<Ocorrencia> ocorrencia;

    @OneToMany
    private List<Justificativa> justificativas;
}
