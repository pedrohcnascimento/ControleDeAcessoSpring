package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AulaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.AulasDoDiaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.*;
import com.senai.ControleDeAcessoSpring.domain.enums.DiasDaSemana;
import com.senai.ControleDeAcessoSpring.domain.repository.AmbienteRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.ProfessorRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.UnidadeCurricularRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private UnidadeCurricularRepository unidadeCurricularRepository;
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

    public <T extends HorarioBase> T criarHorarioVazio(T horario, Semestre semestre, LocalDate dataInicioSemana) {
        horario.setSemestre(semestre);
        if (horario instanceof HorarioSemanal semanal) {
            semanal.setSemanaReferencia(dataInicioSemana);
        }

        int aulasPorDia = horario.getSemestre().getSubTurma().getTurma().getQtdAulasPorDia();

        List<AulasDoDia> dias = new ArrayList<>();

        for (DiasDaSemana dia : DiasDaSemana.values()) {
            AulasDoDia diaObj = new AulasDoDia();
            diaObj.setDiaDaSemana(dia);
            diaObj.setHorario(horario);

            List<Aula> aulas = new ArrayList<>();
            for (int i = 0; i < aulasPorDia; i++) {
                Aula aula = new Aula();
                aula.setOrdem(i);
                aula.setAulasDia(diaObj);
                aulas.add(aula);
            }
            diaObj.setAulas(aulas);
            dias.add(diaObj);
        }

        horario.setListaDeAulasDoDia(dias);
        return horario;
    }


    @Transactional
    public void preencherHorario(HorarioBase horario, List<AulasDoDiaDto> aulasDoDiaDto) {

        for (int i = 0; i < aulasDoDiaDto.size(); i++) {
            AulasDoDiaDto aulasDoDiaDtoAtualizada = aulasDoDiaDto.get(i);
            AulasDoDia aulasDoDiaExistente = horario.getListaDeAulasDoDia().get(i);

            aulasDoDiaExistente.setDiaDaSemana(aulasDoDiaDtoAtualizada.diaDaSemana());

            List<Aula> aulasExistentes = aulasDoDiaExistente.getAulas();

            for (int j = 0; j < aulasDoDiaDtoAtualizada.aulas().size(); j++) {
                AulaDto aulaDTO = aulasDoDiaDtoAtualizada.aulas().get(j);
                Aula aulaExistente = aulasExistentes.get(j);

                aulaExistente.setOrdem(j);
                aulaExistente.setAulasDia(aulasDoDiaExistente);

                if (aulaDTO.unidadeCurricularId() != null) {
                    unidadeCurricularRepository.findById(aulaDTO.unidadeCurricularId())
                            .ifPresent(aulaExistente::setUnidadeCurricular);
                } else {
                    aulaExistente.setUnidadeCurricular(null);
                }

                if (aulaDTO.professorId() != null) {
                    professorRepository.findById(aulaDTO.professorId())
                            .ifPresent(aulaExistente::setProfessor);
                } else {
                    aulaExistente.setProfessor(null);
                }

                if (aulaDTO.ambienteId() != null) {
                    ambienteRepository.findById(aulaDTO.ambienteId())
                            .ifPresent(aulaExistente::setAmbiente);
                } else {
                    aulaExistente.setAmbiente(null);
                }
            }
        }
    }
}
