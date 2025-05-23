package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {
    @OneToMany(mappedBy = "aluno")
    private List<Ocorrencia> ocorrencias;

    @OneToMany(mappedBy = "aluno")
    private List<Justificativa> justificativas;

    @ManyToOne
    @JoinColumn(name = "sub_turma_id") // FK na tabela aluno
    private List<SubTurma> subTurmas;
}