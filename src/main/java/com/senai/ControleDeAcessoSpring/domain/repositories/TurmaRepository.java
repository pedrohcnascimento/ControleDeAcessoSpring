package com.senai.ControleDeAcessoSpring.domain.repositories;

import com.senai.ControleDeAcessoSpring.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
