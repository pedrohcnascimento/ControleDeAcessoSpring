package com.senai.ControleDeAcessoSpring.domain.entity;

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

    @Column(nullable = false)
    private TipoJustificativa tipo;

    @Column
    private String descricao;

    @Column(nullable = false)
    private Date dataDeInicio;

    @Column(nullable = false)
    private Integer qtdDias;

    @Column
    private File anexo;

    @Column(nullable = false)
    private Status status;

}
