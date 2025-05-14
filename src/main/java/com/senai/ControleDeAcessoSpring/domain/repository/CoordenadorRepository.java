package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
    List<Coordenador> findByAtivoTrue();
}
