package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UnidadeCurricularRepository extends JpaRepository<UnidadeCurricular,Long> {
    void deleteAllByCursoId(Long cursoId);
}