package com.senai.ControleDeAcessoSpring.interface_ui.controller.curso;

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
    public ResponseEntity<List<AmbienteDto>> listarAtivos() {
        return ResponseEntity.ok(ambienteService.listarAtivos());
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody AmbienteDto dto) {
        ambienteService.cadastrarAmbiente(dto);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbienteDto> buscarPorId(@PathVariable Long id) {
        Optional<AmbienteDto> ambiente = ambienteService.buscarPorId(id);
        if (ambiente.isPresent()) {
            return ResponseEntity.ok(ambiente.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        if (ambienteService.inativarAmbiente(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public Boolean atualizar(@PathVariable Long id, @RequestBody AmbienteDto dto) {
        return ambienteService.atualizarAmbiente(id, dto);
    }


}