package com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.enuns.StatusOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enuns.TipoOcorrencia;
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

    @Column
    private String justificativa;

    @Column
    private Date dataEhora;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusOcorrencia statusOcorrencia;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoOcorrencia tipo;

}
