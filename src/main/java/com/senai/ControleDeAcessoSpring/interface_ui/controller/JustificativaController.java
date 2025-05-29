package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno.JustificativaService;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("justificativa")
public class JustificativaController {

    @Autowired
    JustificativaService justificativaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarJustificativa(@RequestBody JustificativaDto dto) {
        justificativaService.cadastrarJustificativa(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JustificativaDto>> listarJustificativas() {
        return ResponseEntity.ok(justificativaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JustificativaDto> buscarPorId(@PathVariable Long id) {
        return justificativaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarStatus(@PathVariable Long id,
                                                @RequestBody StatusDaJustificativa status) {
        if (justificativaService.alterarStatus(id, status)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativar(@PathVariable Long id) {
        if (justificativaService.inativar(id)) {
            return ResponseEntity.ok("Justificativa inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}