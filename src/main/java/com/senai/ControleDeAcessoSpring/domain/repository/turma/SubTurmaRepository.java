package com.senai.ControleDeAcessoSpring.domain.repository.turma;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTurmaRepository extends JpaRepository<SubTurma, Long> {
    SubTurma findByAlunos(List<Aluno> alunos);
}
