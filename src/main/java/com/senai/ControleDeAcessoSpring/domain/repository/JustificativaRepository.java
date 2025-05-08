package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
}
