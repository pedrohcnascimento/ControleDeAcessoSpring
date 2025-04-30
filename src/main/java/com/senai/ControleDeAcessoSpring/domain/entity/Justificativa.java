package com.senai.ControleDeAcessoSpring.domain.entity;

import com.senai.ControleDeAcessoSpring.domain.entity.enums.Status;
import com.senai.ControleDeAcessoSpring.domain.entity.enums.TipoJustificativa;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private TipoJustificativa tipo;

    @ManyToOne
    private Aluno aluno;

    @Column
    private String descricao;

    @Column(nullable = false)
    private Date dataDeInicio;

    @Column(nullable = false)
    private Integer qtdDias;

    @Column
    private File anexo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, updatable = false)
    private final Integer prazoDeAceite = 7;

}
