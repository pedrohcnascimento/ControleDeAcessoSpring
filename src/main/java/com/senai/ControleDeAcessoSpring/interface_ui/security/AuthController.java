package com.senai.ControleDeAcessoSpring.interface_ui.security;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.UsuarioDto;
import com.senai.ControleDeAcessoSpring.aplication.security.AuthService;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.UsuarioService;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import com.senai.ControleDeAcessoSpring.infrastructure.util.JwtUtil;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        Usuario user = authService.authenticate(username, password);
        String token = jwtUtil.generateToken(user.getId(), user.getCpf());
        return ResponseEntity.ok(Map.of("token", token));
    }

}