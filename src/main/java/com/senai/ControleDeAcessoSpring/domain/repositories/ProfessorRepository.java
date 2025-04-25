package com.senai.ControleDeAcessoSpring.domain.repositories;

import com.senai.ControleDeAcessoSpring.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
