package com.senai.ControleDeAcessoSpring.Repository;

import com.senai.ControleDeAcessoSpring.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
