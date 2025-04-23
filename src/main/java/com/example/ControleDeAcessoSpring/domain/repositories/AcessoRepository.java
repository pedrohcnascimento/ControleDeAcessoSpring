package com.example.ControleDeAcessoSpring.domain.repositories;

import com.example.ControleDeAcessoSpring.domain.entity.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
