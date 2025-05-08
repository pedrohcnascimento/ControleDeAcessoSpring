package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.ListarUsuarioDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UsuarioDto;
import com.senai.ControleDeAcessoSpring.aplication.service.UsuarioService;
import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto){
        usuarioService.cadastrarUsuario(usuarioDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ListarUsuarioDto>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarUsuarioDto> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto){
        if (!usuarioService.atualizar(id, usuarioDto)) {
            return ResponseEntity.badRequest().body("Falha ao atualizar");
        }
        return ResponseEntity.ok("Atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
        if (usuarioService.deletar(id)) {
            return ResponseEntity.ok("Usuario deletado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

}