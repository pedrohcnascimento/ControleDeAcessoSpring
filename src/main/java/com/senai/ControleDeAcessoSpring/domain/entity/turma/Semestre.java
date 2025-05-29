package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioSemanal;
import com.senai.ControleDeAcessoSpring.domain.service.HorarioService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private String nomeDaTurma;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "horario_padrao_id")
    private HorarioPadrao horarioPadrao;

    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private List<HorarioSemanal> horarioSemanais;

    @ManyToOne
    @JoinColumn(name = "sub_turma_id")
    private SubTurma subTurma;
}
