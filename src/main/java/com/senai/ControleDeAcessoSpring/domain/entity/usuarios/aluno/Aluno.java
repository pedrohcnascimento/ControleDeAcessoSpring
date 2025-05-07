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
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {
    @ManyToMany(mappedBy = "aluno")
    private List<Ocorrencia> subTurma;

    @OneToMany(mappedBy = "aluno")
    private List<Ocorrencia> ocorrencia;

    @ManyToMany
    private List<Justificativa> justificativas;
}
