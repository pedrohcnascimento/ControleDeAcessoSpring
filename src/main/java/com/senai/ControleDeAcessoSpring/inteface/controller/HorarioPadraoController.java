package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.HorarioPadraoDto;
import com.senai.ControleDeAcessoSpring.aplication.service.HorarioPadraoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario-padrao")
public class HorarioPadraoController {

    @Autowired
    private HorarioPadraoService service;

    @PostMapping("/{semestreId}")
    public ResponseEntity<Void> salvar(@PathVariable Long semestreId, @RequestBody HorarioPadraoDto dto) {
        service.salvarHorarioPadrao(semestreId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<HorarioPadraoDto>> listarTodos() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioPadraoDto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody HorarioPadraoDto dto) {
        if (service.atualizar(id, dto)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
