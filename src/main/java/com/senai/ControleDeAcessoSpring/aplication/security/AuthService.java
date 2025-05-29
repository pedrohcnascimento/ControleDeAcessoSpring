package com.senai.ControleDeAcessoSpring.aplication.security;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario authenticate(String username, String password) {
        if (username.contains("@")){
            return usuarioRepository.findByEmail(username)
                    .filter(user -> passwordEncoder.matches(password, user.getSenha()))
                    .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
        } else if (username.length() == 11) {
            return usuarioRepository.findByCpf(username)
                    .filter(user -> passwordEncoder.matches(password, user.getSenha()))
                    .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
        }else throw new RuntimeException("Formato de username inválido");
    }

}