package com.senai.ControleDeAcessoSpring.domain.repository.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
