package com.example.ControleDeAcessoSpring.domain.repositories;

import com.example.ControleDeAcessoSpring.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
