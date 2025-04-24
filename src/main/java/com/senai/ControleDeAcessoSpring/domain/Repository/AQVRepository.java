package com.senai.ControleDeAcessoSpring.domain.Repository;

import com.senai.ControleDeAcessoSpring.domain.Entity.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AQVRepository extends JpaRepository<Coordenador, Long> {
}
