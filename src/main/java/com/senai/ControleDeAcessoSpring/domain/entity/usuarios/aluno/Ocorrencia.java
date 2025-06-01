package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeOcorrencia tipo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDaOcorrencia status;

    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraConclusao;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Professor professorResponsavel;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;

    @Column(name = "ativo")
    private boolean ativo;

    public Ocorrencia(TipoDeOcorrencia tipo,
                      String descricao,
                      StatusDaOcorrencia status,
                      LocalDateTime dataHoraCriacao,
                      LocalDateTime dataHoraConclusao,
                      Aluno aluno,
                      Professor professorResponsavel,
                      UnidadeCurricular unidadeCurricular,
                      boolean ativo) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = status;
        this.dataHoraCriacao = dataHoraCriacao;
        this.dataHoraConclusao = dataHoraConclusao;
        this.aluno = aluno;
        this.professorResponsavel = professorResponsavel;
        this.unidadeCurricular = unidadeCurricular;
        this.ativo = ativo;
    }
}
