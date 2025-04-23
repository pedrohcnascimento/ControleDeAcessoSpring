package com.senai.ControleDeAcessoSpring.domain.Repository;

import com.senai.ControleDeAcessoSpring.domain.Entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
