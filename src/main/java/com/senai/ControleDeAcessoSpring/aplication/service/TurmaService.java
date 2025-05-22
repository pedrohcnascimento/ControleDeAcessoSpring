package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CursoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.TurmaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.UnidadeCurricularDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.Curso;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.repository.CursoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void cadastrarTurma(TurmaDto dto) {
        Turma novaTurma = new Turma();

        novaTurma.setNome(dto.nome());
        novaTurma.setPeriodo(dto.periodo());
        novaTurma.setDataInicial(dto.dataInicial());
        novaTurma.setHorarioEntrada(dto.horarioEntrada());
        novaTurma.setQtdSemestres(dto.qtdSemestres());
        novaTurma.setQtdAulasporDia(dto.qtdAulasPorDia());
        //novaTurma.setCurso(cursoRepository.findById(dto.curso().id()).get());
        novaTurma.setStatus(dto.status());

        turmaRepository.save(novaTurma);
    }

    public List<TurmaDto> listar() {
        return turmaRepository.findAll().stream().map(t -> new TurmaDto(
                t.getNome(),
                t.getPeriodo(),
                t.getDataInicial(),
                t.getHorarioEntrada(),
                t.getQtdSemestres(),
                t.getQtdAulasporDia(),
                /*new CursoDto(
                        t.getCurso().getId(),
                        t.getCurso().getTitulo(),
                        t.getCurso().getTipo(),
                        t.getCurso().getCargaHoraria(),
                        t.getCurso().getToleranciaMinutos(),
                        t.getCurso().getUnidadeCurriculares().stream().map(uc -> new UnidadeCurricularDto(
                                uc.getId(),
                                uc.getNome(),
                                uc.getCargaHorariaTotal(),
                                uc.getCargaHorariaPorSemestre().size()
                        )).toList()
                ),*/
                t.getStatus()
        )).toList();
    }

    public TurmaDto BuscarPorId(Long id) {
        Turma busca = turmaRepository.findById(id).get();
        TurmaDto turma = new TurmaDto(
                busca.getNome(),
                busca.getPeriodo(),
                busca.getDataInicial(),
                busca.getHorarioEntrada(),
                busca.getQtdSemestres(),
                busca.getQtdAulasporDia(),
                /*new CursoDto(
                        busca.getCurso().getId(),
                        busca.getCurso().getTitulo(),
                        busca.getCurso().getTipo(),
                        busca.getCurso().getCargaHoraria(),
                        busca.getCurso().getToleranciaMinutos(),
                        busca.getCurso().getUnidadeCurriculares().stream().map(uc -> new UnidadeCurricularDto(
                                uc.getId(),
                                uc.getNome(),
                                uc.getCargaHorariaTotal(),
                                uc.getCargaHorariaPorSemestre().size()
                        )).toList()
                ),*/
                busca.getStatus()
        );
        return turma;
    }

    public Boolean deletarTurma(Long id) {
        return turmaRepository.findById(id).map(t -> {
            t.setStatus(false);
            turmaRepository.save(t);
            return true;
        }).orElse(false);
    }

    public Boolean atualizarTurma(Long id, TurmaDto novaTurma) {
        return turmaRepository.findById(id).map(t -> {
            t.setNome(novaTurma.nome());
            t.setPeriodo(novaTurma.periodo());
            t.setHorarioEntrada(novaTurma.horarioEntrada());
            t.setQtdSemestres(novaTurma.qtdSemestres());
            t.setDataInicial(novaTurma.dataInicial());
            turmaRepository.save(t);
            return true;
        }).orElse(false);
    }
}
