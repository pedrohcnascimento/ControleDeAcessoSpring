package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.TurmaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.repository.curso.CursoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.TurmaRepository;
import com.senai.ControleDeAcessoSpring.domain.service.HorarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private HorarioService horarioService;

    @Transactional
    public void cadastrarTurma(TurmaDto dto) {
        Turma turma = dto.fromDto();

        Curso curso = cursoRepository.findById(dto.cursoId())
                        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        turma.setCurso(curso);

        SubTurma subTurma = new SubTurma();
        turma.getSubTurmas().add(subTurma);

        subTurma.setNome("Turma " + turma.getSubTurmas().size());
        subTurma.setTurma(turma);

        Semestre semestre = new Semestre();
        subTurma.getSemestres().add(semestre);
        semestre.setNomeDaTurma(
                subTurma.getSemestres().size() +
                        subTurma.getTurma().getSiglaDaTurma() +
                        subTurma.getTurma().getPeriodo().getSigla()
        );

        semestre.setSubTurma(subTurma);
        semestre.setNumero(subTurma.getSemestres().size());

        semestre.setHorarioPadrao(horarioService.criarHorarioPadraoVazio(semestre));
        semestre.setHorarioSemanais(new ArrayList<>());

        turmaRepository.save(turma);
    }
    
    public List<TurmaDto> listar() {
        return turmaRepository.findAll().stream()
                .map(TurmaDto::toDto).toList();
    }

    public Optional<TurmaDto> BuscarPorId(Long id) {
        return turmaRepository.findById(id).map(TurmaDto::toDto);
    }

    @Transactional
    public Boolean deletarTurma(Long id) {
        Optional<Turma> opcional = turmaRepository.findById(id);
        if (opcional.isEmpty()) return false;
        turmaRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Boolean atualizarTurma(Long id, TurmaDto dto) {
        Optional<Turma> optional = turmaRepository.findById(id);
        if (optional.isEmpty()) return false;

        Turma turma = optional.get();
        turma.setSiglaDaTurma(dto.sigla());
        turma.setPeriodo(dto.periodo());
        turma.setDataInicial(dto.dataInicial());
        turma.setQtdSemestres(dto.qtdSemestres());
        turma.setHorarioEntrada(dto.horarioEntrada());
        turma.setQtdAulasporDia(dto.qtdAulasPorDia());

        turmaRepository.save(turma);
        return true;
    }
}
