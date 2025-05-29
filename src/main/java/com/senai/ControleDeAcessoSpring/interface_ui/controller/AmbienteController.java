package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.curso.AmbienteDto;
import com.senai.ControleDeAcessoSpring.aplication.service.curso.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

    @Autowired
    AmbienteService ambienteService;

    @GetMapping
    public ResponseEntity<List<AmbienteDto>> listar() {
        return ResponseEntity.ok(ambienteService.listar());
    }

    @PostMapping
    public void adicionar(@RequestBody AmbienteDto dto) {
        ambienteService.cadastrarAmbiente(dto);
    }

    @GetMapping("/ambiente/{id}")
    public ResponseEntity<AmbienteDto> listarPorId(@PathVariable Long id) {
        Optional<AmbienteDto> ambiente = ambienteService.listarPorId(id);
        if (ambiente.isPresent()) {
            return ResponseEntity.ok(ambiente.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public Boolean inativar(@PathVariable Long id) {
        return ambienteService.inativarAmbiente(id);
    }

    @PutMapping("/{id}")
    public Boolean atualizar(@PathVariable Long id, @RequestBody AmbienteDto dto) {
        return ambienteService.atualizarAmbiente(id, dto);
    }


}