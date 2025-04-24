package com.example.ControleDeAcessoSpring.domain.repositories;

import com.example.ControleDeAcessoSpring.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
