package com.senai.ControleDeAcessoSpring.domain.repository.curso;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
