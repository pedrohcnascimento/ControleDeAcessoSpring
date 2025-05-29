<<<<<<<< HEAD:src/main/java/com/senai/ControleDeAcessoSpring/inteface/controller/CoordenadorController.java
package com.senai.ControleDeAcessoSpring.inteface.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.AQVDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.CoordenadorDto;
import com.senai.ControleDeAcessoSpring.aplication.service.AQVService;
import com.senai.ControleDeAcessoSpring.aplication.service.CoordenadorService;
========
package com.senai.ControleDeAcessoSpring.interface_ui.controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.CoordenadorDto;
import com.senai.ControleDeAcessoSpring.aplication.service.usuarios.CoordenadorService;
>>>>>>>> master:src/main/java/com/senai/ControleDeAcessoSpring/interface_ui/controller/CoordenadorController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {

    @Autowired
    CoordenadorService coordenadorService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCoordenador(@RequestBody CoordenadorDto dto) {
        coordenadorService.cadastrarCoordenador(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordenadorDto> buscarPorId(@PathVariable Long id) {
        return coordenadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDto>> listarAtivos() {
        return ResponseEntity.ok(coordenadorService.listarAtivos());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody CoordenadorDto dto) {
        if (coordenadorService.atualizar(id, dto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        if (coordenadorService.inativar(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

