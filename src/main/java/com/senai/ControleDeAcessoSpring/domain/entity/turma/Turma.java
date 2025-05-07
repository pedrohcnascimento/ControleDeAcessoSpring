package com.senai.ControleDeAcessoSpring.domain.entity.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date dataDeInicio;
    private LocalDateTime horarioDeEntrada;
    private Integer qtdSemestre;
    private Integer qtdAulasPorDia;

    @OneToMany(mappedBy = "turma")
    private List<SubTurma> subTurmas;

    @ManyToOne
    private Curso curso;
}
