package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeCurricularRepository extends JpaRepository<UnidadeCurricular, Long> {
    List<UnidadeCurricular> findByAtivoTrue();
}
