package com.senai.ControleDeAcessoSpring.domain.entity.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Data
public class UnidadeCurricular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer cargaHorariaTotal;

    @ManyToOne
    private Curso curso;

    @ManyToMany
    private List<Professor> professores;

    public UnidadeCurricular(String nome, Integer cargaHorariaTotal) {
        this.nome = nome;
        this.cargaHorariaTotal = cargaHorariaTotal;
    }
}
