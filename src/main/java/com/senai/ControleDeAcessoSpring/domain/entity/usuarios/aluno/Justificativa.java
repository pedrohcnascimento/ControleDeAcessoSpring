package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeJustificativa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Justificativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoDeJustificativa tipo;

    private String descricao;
    private String anexo;
    private LocalDate dataInicial;
    private Integer qtdDias;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraConclusao;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private StatusDaJustificativa status;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}