package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horarios.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horarios.HorarioSemanal;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SubTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Turma turma;

    @ManyToMany(mappedBy = "subTurmas")
    private List<Aluno> alunos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario_padrao_id")
    private HorarioPadrao horarioPadrao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_turma_id")
    private List<HorarioSemanal> horariosSemanais;
}
