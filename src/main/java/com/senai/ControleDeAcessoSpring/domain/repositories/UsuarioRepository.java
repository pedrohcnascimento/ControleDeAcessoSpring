package com.senai.ControleDeAcessoSpring.domain.repositories;

import com.senai.ControleDeAcessoSpring.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
