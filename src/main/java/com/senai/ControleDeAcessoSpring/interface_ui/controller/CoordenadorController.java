package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {

    @Autowired
    private CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<String> salvar (@RequestBody CoordenadorDto coordenadorDto) {
        coordenadorService.salvar(coordenadorDto);
        return ResponseEntity.ok("Coordenador salvo com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDto>> listarCoordenadores() {
        return ResponseEntity.ok(coordenadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDto> buscarPorId(@PathVariable Long id) {
        return coordenadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody CoordenadorDto coordenadorDto) {
        if (coordenadorService.atualizar(id, coordenadorDto)) {
            return ResponseEntity.ok("Coordenador atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (coordenadorService.deletar(id)) {
            return ResponseEntity.ok("Coordenador deletado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
