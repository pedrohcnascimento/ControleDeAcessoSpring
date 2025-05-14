package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {
    @OneToMany(mappedBy = "aluno")
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "aluno")
    private List<Justificativa> justificativas;

    @ManyToMany
    private List<SubTurma> subTurmas;
}
