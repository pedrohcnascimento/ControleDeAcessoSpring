package com.senai.ControleDeAcessoSpring.domain.repositories;

import com.senai.ControleDeAcessoSpring.domain.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}
