package com.senai.ControleDeAcessoSpring.domain.repository.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}
