package com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.AQV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AqvRepository extends JpaRepository<AQV, Long> {
    List<AQV> findByAtivoTrue();
}
