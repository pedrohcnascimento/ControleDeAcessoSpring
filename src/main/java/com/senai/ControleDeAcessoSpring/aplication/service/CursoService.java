package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public void cadastrarCurso(CursoDto cursoDto){
        Curso curso = new Curso();

        curso.setTitulo(cursoDto.titulo());
        curso.setTipo(cursoDto.tipo());
        curso.setCargaHoraria(cursoDto.cargaHoraria());
        curso.setToleranciaMinutos(cursoDto.toleranciaMinutos());
//        curso.setUnidadeCurriculares(mapUnidadeCurriculares(cursoDto.unidadeCurriculares()));
    }

//    private List<UnidadeCurricular> mapUnidadeCurriculares(List<UnidadeCurricularDto> unidadeCurricularesDto) {
//
//
//    }
}
