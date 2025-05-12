package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        curso.setUnidadeCurriculares(mapUnidadeCurriculares(cursoDto.unidadeCurriculares(), curso));
        
        cursoRepository.save(curso);
    }

    private List<UnidadeCurricular> mapUnidadeCurriculares(List<UnidadeCurricularDto> unidadeCurricularesDto, Curso curso) {
        return unidadeCurricularesDto.stream()
                .map(dto -> {
                    UnidadeCurricular uc = new UnidadeCurricular();
                    uc.setNome(dto.nome());
                    uc.setCargaHorariaTotal(dto.cargarHorariaPorSemestre());
                    uc.setCurso(curso);
                    return uc;
                })
                .toList();
    }

    public List<CursoDto> listarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(curso -> new CursoDto(
                        curso.getId(),
                        curso.getTitulo(),
                        curso.getTipo(),
                        curso.getCargaHoraria(),
                        curso.getToleranciaMinutos(),
                        mapUnidadeCurricularesParaDto(curso.getUnidadeCurriculares())
                )).toList();
    }

    private List<UnidadeCurricularDto> mapUnidadeCurricularesParaDto(List<UnidadeCurricular> unidadeCurriculares) {
        return unidadeCurriculares.stream()
                .map(uc -> new UnidadeCurricularDto(
                        uc.getId(),
                        uc.getNome(),
                        uc.getCargaHorariaPorSemestre().size(),
                        uc.getCargaHorariaTotal()
                )).toList();
    }

    public Optional<CursoDto> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .map(curso -> new CursoDto(
                        curso.getId(),
                        curso.getTitulo(),
                        curso.getTipo(),
                        curso.getCargaHoraria(),
                        curso.getToleranciaMinutos(),
                        mapUnidadeCurricularesParaDto(curso.getUnidadeCurriculares())
                ));
    }

    public boolean atualizarCurso(Long id, CursoDto cursoDto) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setTitulo(cursoDto.titulo());
                    curso.setTipo(cursoDto.tipo());
                    curso.setCargaHoraria(cursoDto.cargaHoraria());
                    curso.setToleranciaMinutos(cursoDto.toleranciaMinutos());
                    cursoRepository.save(curso);
                    return true;
                }).orElse(false);
    }

    public boolean deletarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
