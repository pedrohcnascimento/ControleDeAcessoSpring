package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.TurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping
    public String cadastrarTurma(@RequestBody TurmaDto turma) {
        service.cadastrarTurma(turma);
        return "Turma criada com sucesso!";
    }

    @GetMapping
    public List<TurmaDto> listarTurmas() {
        return service.listar();
    }
}
