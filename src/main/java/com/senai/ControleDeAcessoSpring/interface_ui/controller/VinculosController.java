package com.senai.ControleDeAcessoSpring.inteface.Controller;

import com.senai.ControleDeAcessoSpring.aplication.dto.SubTurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vinculos")
public class VinculosController {

    @Autowired
    TurmaService turmaService;

    @PostMapping("/vincularCursoATurma")
    public void vincularACurso(@RequestBody Long idTurma,@RequestBody Long idCurso){
        turmaService.vincularACurso(idTurma, idCurso);
    }

//    @PostMapping("/adicionarSubturmas")
//    public void adicionarSubTurma(@RequestBody Long idTurma, @RequestBody SubTurmaDto){
//        turmaService.adicionarSubTurma(idTurma, );
//    }
}
