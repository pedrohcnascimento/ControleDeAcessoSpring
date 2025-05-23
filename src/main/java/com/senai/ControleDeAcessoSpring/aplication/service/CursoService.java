package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.CursoRepository;

import com.senai.ControleDeAcessoSpring.domain.repository.UnidadeCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UnidadeCurricularRepository unidadeCurricularRepository;

    public CursoDto salvar(CursoDto dto) {

        final Curso curso = cursoRepository.save(
                new Curso(
                        dto.titulo(),
                        dto.tipoDeCurso(),
                        dto.cargaHoraria(),
                        dto.tolerancia()
                )
        );

        List<UnidadeCurricular> unidades = dto.unidadesCuricularesDto()
                .stream()
                .map(
                        ucDto -> ucDto.fromDto(curso)
                )
                .toList();

        unidadeCurricularRepository.saveAll(unidades);

        curso.setUnidadesCurriculares(unidades);

        return CursoDto.toDto(curso);
    }

    public List<CursoDto> listarTodos() {
        return cursoRepository.findAll().stream().map(CursoDto::toDto).toList();
    }

    public Optional<CursoDto> buscarPorId(Long id) {
        return cursoRepository.findById(id).map(CursoDto::toDto);
    }

    public CursoDto atualizar(Long id, CursoDto dto) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setTitulo(dto.titulo());
        curso.setTipoDeCurso(dto.tipoDeCurso());
        curso.setCargaHoraria(dto.cargaHoraria());
        curso.setToleranciaMinutos(dto.tolerancia());

        final Curso cursoAtualizado = cursoRepository.save(curso);

        unidadeCurricularRepository.deleteAllByCursoId(curso.getId());

        List<UnidadeCurricular> novasUCs = dto.unidadesCuricularesDto()
                .stream()
                .map(ucDto -> ucDto.fromDto(cursoAtualizado))
                .toList();

        unidadeCurricularRepository.saveAll(novasUCs);

        cursoAtualizado.setUnidadesCurriculares(novasUCs);

        return CursoDto.toDto(cursoAtualizado);
    }

    public void deletar(Long id) {
        if (cursoRepository.existsById(id)) {
            unidadeCurricularRepository.deleteAllByCursoId(id);
            cursoRepository.deleteById(id);
        }
    }
}