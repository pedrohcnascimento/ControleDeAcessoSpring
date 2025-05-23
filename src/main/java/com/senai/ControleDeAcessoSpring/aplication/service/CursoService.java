package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.curso.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.curso.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.repository.curso.CursoRepository;

import com.senai.ControleDeAcessoSpring.domain.repository.curso.UnidadeCurrricularRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.horario.UnidadeCurricularRepository;
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
                        dto.tipo(),
                        dto.cargaHoraria(),
                        dto.toleranciaMinutos()
                )
        );

        List<UnidadeCurricular> unidades = dto.unidadesCurriculares()
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
        curso.setTipo(dto.tipo());
        curso.setCargaHoraria(dto.cargaHoraria());
        curso.setToleranciaMinutos(dto.toleranciaMinutos());

        final Curso cursoAtualizado = cursoRepository.save(curso);

        unidadeCurricularRepository.deleteById(curso.getId());

        List<UnidadeCurricular> novasUCs = dto.unidadesCurriculares()
                .stream()
                .map(ucDto -> ucDto.fromDto(cursoAtualizado))
                .toList();

        unidadeCurricularRepository.saveAll(novasUCs);

        cursoAtualizado.setUnidadesCurriculares(novasUCs);

        return CursoDto.toDto(cursoAtualizado);
    }

    public Boolean deletar(Long id) {
        if (cursoRepository.existsById(id)) {
            unidadeCurricularRepository.deleteById(id);
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}