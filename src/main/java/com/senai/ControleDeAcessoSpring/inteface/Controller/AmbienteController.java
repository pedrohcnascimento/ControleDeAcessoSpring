package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.seusistema.controleacesso.ambiente.dto.AmbienteDTO;
import com.seusistema.controleacesso.ambiente.service.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteService ambienteService;

    @PostMapping
    public ResponseEntity<AmbienteDTO> criar(@RequestBody AmbienteDTO ambienteDTO) {
        AmbienteDTO novoAmbiente = ambienteService.criar(ambienteDTO);
        return new ResponseEntity<>(novoAmbiente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AmbienteDTO>> listar() {
        List<AmbienteDTO> ambientes = ambienteService.listarAtivos();
        return new ResponseEntity<>(ambientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbienteDTO> buscarPorId(@PathVariable Long id) {
        AmbienteDTO ambienteDTO = ambienteService.buscarPorId(id);
        if (ambienteDTO != null) {
            return new ResponseEntity<>(ambienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmbienteDTO> atualizar(@PathVariable Long id, @RequestBody AmbienteDTO ambienteDTO) {
        AmbienteDTO ambienteAtualizado = ambienteService.atualizar(id, ambienteDTO);
        if (ambienteAtualizado != null) {
            return new ResponseEntity<>(ambienteAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ambienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
