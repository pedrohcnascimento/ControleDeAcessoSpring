package com.senai.ControleDeAcessoSpring.domain.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario.AulaDoDiaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.turma.horario.AulaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.*;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import com.senai.ControleDeAcessoSpring.domain.repository.curso.AmbienteRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.ProfessorRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.curso.UnidadeCurrricularRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private UnidadeCurrricularRepository unidadeCurrricularRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AmbienteRepository ambienteRepository;

    public HorarioPadrao criarHorarioPadraoVazio(Semestre semestre) {
        return criarHorarioVazio(new HorarioPadrao(), semestre, null);
    }

    public HorarioSemanal criarHorarioSemanalVazio(Semestre semestre, LocalDate segundaFeira) {
        return criarHorarioVazio(new HorarioSemanal(), semestre, segundaFeira);
    }

    public <T extends HorarioBase> T criarHorarioVazio(T horario, Semestre semestre, LocalDate dataInicioSemestre) {
        horario.setSemestre(semestre);
        if (horario instanceof HorarioSemanal semanal) {
            semanal.setSemanaReferencia(dataInicioSemestre);
        }

        int aulasPorDia = horario.getSemestre().getSubTurma().getTurma().getQtdAulasPorDia();

        List<AulaDoDia> dias = new ArrayList<>();

        for (DiaDaSemana dia : DiaDaSemana.values()) {
            AulaDoDia diaObj = new AulaDoDia();
            diaObj.setDiaDaSemana(dia);
            diaObj.setHorario(horario);

            List<Aula> aulas = new ArrayList<>();
            for (int i = 0; i < aulasPorDia; i++) {
                Aula aula = new Aula();
                aula.setOrdem(i);
                aula.setAulasDoDia(diaObj);
                aulas.add(aula);
            }
            diaObj.setAulas(aulas);
            dias.add(diaObj);
        }

        horario.setAulasDoDia(dias);
        return horario;
    }

    @Transactional
    public void preencherHorario(HorarioBase horario, List<AulaDoDiaDto> aulaDoDiaDtos) {
        for (int i = 0; i < aulaDoDiaDtos.size(); i++) {
            AulaDoDiaDto aulasDoDiaDtoAtualizada = aulaDoDiaDtos.get(i);
            AulaDoDia aulasDoDiaExistentes = horario.getAulasDoDia().get(i);

            aulasDoDiaExistentes.setDiaDaSemana(aulasDoDiaDtoAtualizada.diaDaSemana());

            List<Aula> aulasExistentes = aulasDoDiaExistentes.getAulas();

            for (int j = 0; j < aulasDoDiaDtoAtualizada.aulas().size(); j++) {
                AulaDto aulaDto = aulasDoDiaDtoAtualizada.aulas().get(j);
                Aula aulaExistente = aulasExistentes.get(j);

                aulaExistente.setOrdem(j);
                aulaExistente.setAulasDoDia(aulasDoDiaExistentes);

                if(aulaDto.unidadeCurricularId() != null) {
                    unidadeCurrricularRepository.findById(aulaDto.unidadeCurricularId())
                            .ifPresent(aulaExistente::setUnidadeCurricular);
                } else {
                    aulaExistente.setUnidadeCurricular(null);
                }

                if(aulaDto.professorId() != null) {
                    professorRepository.findById(aulaDto.professorId())
                            .ifPresent(aulaExistente::setProfessor);
                } else {
                    aulaExistente.setProfessor(null);
                }

                if(aulaDto.ambientId() != null) {
                    ambienteRepository.findById(aulaDto.ambientId())
                            .ifPresent(aulaExistente::setAmbiente);
                } else {
                    aulaExistente.setAmbiente(null);
                }
            }
        }
    }
}
