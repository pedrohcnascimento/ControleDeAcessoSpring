package com.example.ControleDeAcessoSpring.model.repositorys;

import com.example.ControleDeAcessoSpring.model.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
