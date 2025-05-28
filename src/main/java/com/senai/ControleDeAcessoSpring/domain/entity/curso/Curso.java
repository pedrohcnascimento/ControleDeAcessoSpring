package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.enuns.TipoDeCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoDeCurso tipo;

    private Integer cargaHoraria;
    private Integer toleranciaMinutos;

    @OneToMany(mappedBy = "curso")
    private List<UnidadeCurricular> unidadesCurriculares;

    public Curso(
            String titulo,
            TipoDeCurso tipo,
            Integer cargaHoraria,
            Integer toleranciaMinutos
    ) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.cargaHoraria = cargaHoraria;
        this.toleranciaMinutos = toleranciaMinutos;
    }
}
