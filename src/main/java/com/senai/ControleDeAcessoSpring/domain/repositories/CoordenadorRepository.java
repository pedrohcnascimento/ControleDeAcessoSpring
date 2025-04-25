package com.senai.ControleDeAcessoSpring.domain.repositories;

import com.senai.ControleDeAcessoSpring.domain.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
}
