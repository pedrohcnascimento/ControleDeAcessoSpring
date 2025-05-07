package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioSemanal;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubTurma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "subTurmas")
    private List<Aluno> alunos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario_padrao_id")
    private HorarioPadrao horarioPadrao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_turma_id")
    private List<HorarioSemanal> horariosSemanais;

    @ManyToOne
    private Turma turma;
}
