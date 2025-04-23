package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.domain.Entity.Usuario;
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
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuariosById(@PathVariable long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuarios(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuarios(@PathVariable long id, @RequestBody Usuario usuarioAtualizado){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()){
            Usuario usuario = usuarioExistente.get();

            usuario.setIdAcesso(usuarioAtualizado.getIdAcesso());
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setFoto(usuarioAtualizado.getFoto());

            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Usuario> deleteUsuarios(@PathVariable long id){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()){
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.notFound().build();
    }

}