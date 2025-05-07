package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
