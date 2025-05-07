package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeOcorrencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDateTime dataEHoraDaCriacao;
    private LocalDateTime dataEHoraDaConclusao;

    @Enumerated(EnumType.STRING)
    private StatusDaOcorrencia statusOcorrencia;

    @Enumerated(EnumType.STRING)
    private TipoDeOcorrencia tipoDeOcorrencia;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private UnidadeCurricular unidadeCurricular;
}
