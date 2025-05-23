package com.senai.ControleDeAcessoSpring.domain.repository.usuarios;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByAtivoTrue();
}
