package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.AmbienteDto;
import com.senai.ControleDeAcessoSpring.aplication.service.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {
    @Autowired
    AmbienteService ambienteService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAmbiente(@RequestBody AmbienteDto dto) {
        ambienteService.cadastrarAmbiente(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbienteDto> buscarPorId(@PathVariable Long id) {
        return ambienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<AmbienteDto>> listarAtivos() {
        return ResponseEntity.ok(ambienteService.listarAtivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AmbienteDto dto) {
        if (ambienteService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        if (ambienteService.inativar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
