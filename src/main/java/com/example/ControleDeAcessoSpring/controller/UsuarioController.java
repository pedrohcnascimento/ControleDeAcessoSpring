package com.example.ControleDeAcessoSpring.controller;

import com.example.ControleDeAcessoSpring.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final static List<Usuario> listaUsuario = new ArrayList<>();

    public UsuarioController() {
        listaUsuario.add(new Aluno(1, 12, "Dener", 122123312, "ndue@gmail.com", "-"));
        listaUsuario.add(new Professor(2, 13, "Glauber", 122121222, "ndue@gmail.com", "-"));
        listaUsuario.add(new Coordenador(3, 14, "Mario", 1221212, "ndue@gmail.com", "-"));
        listaUsuario.add(new AQV(4, 15, "Tatiane", 1221212, "ndue@gmail.com", "-"));
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return listaUsuario;
    }

    @GetMapping("/{cargo}")
    public List getUsuarios( @PathVariable String cargo) {
        List lista = new ArrayList<>();
        for (Usuario u : listaUsuario) {
            if (u.getCargo().equals(cargo)) {
                lista.add(u);
            }
        }
        return lista;
    }

    @PostMapping
    public String adicionarUsuario(@RequestBody Usuario usuario) {
        listaUsuario.add(usuario);
        return "Usuário adicionado com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizarUsuario(@RequestBody Usuario usuarioNovo, @PathVariable Integer id) {
        if (buscarUsuario(id) != null) {
            buscarUsuario(id).setNome(usuarioNovo.getNome());
            return "Usuário atualizado!";
        } else {
            return "Usuário não encontrado.";
        }
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioPorId(@PathVariable Integer id) {
        return buscarUsuario(id);
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable Integer id) {
        if (buscarUsuario(id) != null) {
            listaUsuario.remove(buscarUsuario(id));
            return "Usuário deletado.";
        } else
            return "Usuário não encontrado.";
    }

    private static Usuario buscarUsuario(Integer id) {
        for (Usuario usuario : listaUsuario) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }
}

