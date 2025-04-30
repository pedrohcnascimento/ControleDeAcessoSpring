package com.senai.ControleDeAcessoSpring.domain.entity;

import com.senai.ControleDeAcessoSpring.domain.entity.enums.StatusOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.entity.enums.TipoOcorrencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    private Aluno aluno;

    @Column(nullable = false)
    private String justificativa;

    @Column(nullable = false)
    private Date dataEhora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcorrencia statusOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOcorrencia tipo;

}
