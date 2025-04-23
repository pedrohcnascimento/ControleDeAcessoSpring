package com.senai.ControleDeAcessoSpring.domain.Repository;

import com.senai.ControleDeAcessoSpring.domain.Entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
}
