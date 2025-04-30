package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.UsuarioDto;
import com.senai.ControleDeAcessoSpring.aplication.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.salvar(usuarioDto);
        return ResponseEntity.ok("Usuário salvo com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody UsuarioDto usuarioDto) {
        if (usuarioService.atualizar(id, usuarioDto)) {
            return ResponseEntity.ok("Usuário atualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (usuarioService.deletar(id)) {
            return ResponseEntity.ok("Usuário deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
