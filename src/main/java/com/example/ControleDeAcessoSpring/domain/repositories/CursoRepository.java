package com.example.ControleDeAcessoSpring.domain.repositories;

import com.example.ControleDeAcessoSpring.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
