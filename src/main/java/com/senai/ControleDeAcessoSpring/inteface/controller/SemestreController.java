package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.SemestreDto;
import com.senai.ControleDeAcessoSpring.aplication.service.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subturmas/{subTurmaId}/semestres")
public class SemestreController {

    @Autowired
    private SemestreService semestreService;

    @PostMapping
    public ResponseEntity<Void> criarSemestre(@PathVariable Long subTurmaId) {
        semestreService.criarSemestre(subTurmaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SemestreDto>> listarTodos() {
        return ResponseEntity.ok(semestreService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemestreDto> buscarPorId(@PathVariable Long id) {
        return semestreService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody SemestreDto dto) {
        if (semestreService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (semestreService.deletar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
