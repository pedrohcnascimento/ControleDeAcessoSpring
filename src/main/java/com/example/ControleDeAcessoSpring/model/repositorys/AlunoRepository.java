package com.example.ControleDeAcessoSpring.model.repositorys;

import com.example.ControleDeAcessoSpring.model.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
