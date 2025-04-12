package com.example.ControleDeAcessoSpring.model.repositories;

import com.example.ControleDeAcessoSpring.model.entity.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
