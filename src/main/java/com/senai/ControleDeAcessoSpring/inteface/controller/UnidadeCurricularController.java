package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.aplication.service.UnidadeCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uc")
public class UnidadeCurricularController {

    @Autowired
    UnidadeCurricularService unidadeCurricularService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUnidadeCurricular(@RequestBody UnidadeCurricularDto dto) {
        unidadeCurricularService.cadastrarUnidadeCurricular(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UnidadeCurricularDto>> listar() {
        return ResponseEntity.ok(unidadeCurricularService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeCurricularDto> buscarPorId(@PathVariable Long id) {
        return unidadeCurricularService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                                @RequestBody UnidadeCurricularDto dto) {
        if (unidadeCurricularService.atualizar(id, dto)) {
            return ResponseEntity.ok("Unidade curricular atualizada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativar(@PathVariable Long id) {
        if (unidadeCurricularService.inativar(id)) {
            return ResponseEntity.ok("Unidade Curricular inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
