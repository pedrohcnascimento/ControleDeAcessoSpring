package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aqvs")
public class AQVController {

    @Autowired
    private AQVService aqvService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AQVDto aqvDto) {
        aqvService.salvar(aqvDto);
        return ResponseEntity.ok("AQV salvo com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<AQVDto>> listarAQVs() {
        return ResponseEntity.ok(aqvService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AQVDto> buscarPorId(@PathVariable Long id) {
        return aqvService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody AQVDto aqvDto) {
        if (aqvService.atualizar(id, aqvDto)) {
            return ResponseEntity.ok("AQV atualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (aqvService.deletar(id)) {
            return ResponseEntity.ok("AQV deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
