package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeJustificativa;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Justificativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeJustificativa tipo;

    @ManyToOne
    private Aluno aluno;

    private String descricao;
    private String anexo;
    private Date dataDeInicio;
    private Integer qtdDias;

    @Enumerated(EnumType.STRING)
    private StatusDaJustificativa statusJustificativa;

    @Column(nullable = false, updatable = false)
    private final Integer prazoDeAceite = 7;
}
