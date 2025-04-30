package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody OcorrenciaDto ocorrenciaDto) {
        ocorrenciaService.salvar(ocorrenciaDto);
        return ResponseEntity.ok("Ocorrência salva com sucesso!");
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


    //Vai atualizar?
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id,
                                            @RequestBody OcorrenciaDto ocorrenciaDto) {
        if (ocorrenciaService.atualizar(id, ocorrenciaDto)) {
            return ResponseEntity.ok("Ocorrência atualizada!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (ocorrenciaService.deletar(id)) {
            return ResponseEntity.ok("Ocorrência deletada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

