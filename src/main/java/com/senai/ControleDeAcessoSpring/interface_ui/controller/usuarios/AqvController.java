package com.senai.ControleDeAcessoSpring.interface_ui.controller.usuarios;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.AQVDto;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.AqvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aqv")
public class AqvController {

    @Autowired
    AqvService aqvService;

    @PostMapping
    public ResponseEntity<Void> cadastrarAqv(@RequestBody List<AQVDto> dtos) {
        aqvService.cadastrarAqv(dtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AQVDto> buscarPorId(@PathVariable Long id) {
        return aqvService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AQVDto>> listarAtivos() {
        return ResponseEntity.ok(aqvService.listarAtivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AQVDto dto) {
        if (aqvService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        if (aqvService.inativar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}