package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
}
