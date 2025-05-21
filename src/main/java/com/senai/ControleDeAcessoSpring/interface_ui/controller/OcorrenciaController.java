package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.OcorrenciaService;
import com.senai.ControleDeAcessoSpring.domain.enuns.StatusDaOcorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarOcorrencia(@RequestBody OcorrenciaDto dto) {
        ocorrenciaService.cadastrarOcorrencia(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaDto>> listarOcorrencias() {
        return ResponseEntity.ok(ocorrenciaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDto> buscarPorId(@PathVariable Long id) {
        return ocorrenciaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarStatus(@PathVariable Long id,
                                                @RequestBody StatusDaOcorrencia status) {
        if (ocorrenciaService.alterarStatus(id, status)) {
            return ResponseEntity.ok("Status alterado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativar(@PathVariable Long id) {
        if (ocorrenciaService.inativar(id)) {
            return ResponseEntity.ok("Ocorrência inativada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

