package com.example.ControleDeAcessoSpring.domain.repositories;

import com.example.ControleDeAcessoSpring.domain.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
}
