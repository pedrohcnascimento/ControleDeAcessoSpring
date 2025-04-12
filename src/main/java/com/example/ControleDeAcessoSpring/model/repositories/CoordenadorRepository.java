package com.example.ControleDeAcessoSpring.model.repositories;

import com.example.ControleDeAcessoSpring.model.entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
}
