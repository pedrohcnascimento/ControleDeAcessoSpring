package com.senai.ControleDeAcessoSpring.domain.Repository;

import com.senai.ControleDeAcessoSpring.domain.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
