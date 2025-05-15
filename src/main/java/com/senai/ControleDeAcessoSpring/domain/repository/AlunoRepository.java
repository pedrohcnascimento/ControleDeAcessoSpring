package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByAtivoTrue();
}
