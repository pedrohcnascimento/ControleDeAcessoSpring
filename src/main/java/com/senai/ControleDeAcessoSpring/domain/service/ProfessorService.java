package com.senai.ControleDeAcessoSpring.domain.service;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class ProfessorService {
    public Professor encontrarProfessor(Aluno aluno) {
        return acharProfessor(Objects
                        .requireNonNull(
                acharDia(acharSemestre(Objects
                        .requireNonNull(encontrarSubturma(aluno))))),
                LocalTime.of(8, 0));
    }

    private SubTurma encontrarSubturma(Aluno aluno) {
        List<SubTurma> subTurmas = aluno.getSubTurmas();

        for (SubTurma subTurma : subTurmas) {
            if (subTurma.getTurma().getPeriodo() == Periodo.INTEGRAL) {
                return subTurma;
            }
        }

        if (LocalTime.now().isAfter(LocalTime.NOON)) {
            if (LocalTime.now().isAfter(LocalTime.of(18, 0))) {
                for (SubTurma subTurma : subTurmas) {
                    if (subTurma.getTurma().getPeriodo() == Periodo.TARDE) {
                        return subTurma;
                    }
                }
            } else {
                for (SubTurma subTurma : subTurmas) {
                    if (subTurma.getTurma().getPeriodo() == Periodo.NOITE) {
                        return subTurma;
                    }
                }
            }
        } else {
            for (SubTurma subTurma: subTurmas) {
                if (subTurma.getTurma().getPeriodo() == Periodo.MANHA) {
                    return subTurma;
                }
            }
        }

        return null;
    }

    private Semestre acharSemestre(SubTurma subTurma) {
        Long qtdDias = ChronoUnit.DAYS
                .between(
                        subTurma.getTurma().getDataInicial(),
                        LocalDate.now()
                );

        Long qtdMeses = qtdDias / 30;

        return subTurma.getSemestres().get((int) (qtdMeses / 6));
    }

    private AulaDoDia acharDia(Semestre semestre) {
        HorarioPadrao horario = semestre.getHorarioPadrao();

        DiaDaSemana diaAtual = DiaDaSemana.SEGUNDA;

        switch (LocalDate.now().getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"))
        ) {
            case "seg":
                diaAtual = DiaDaSemana.SEGUNDA;
                break;
            case "ter":
                diaAtual = DiaDaSemana.TERCA;
                break;
            case "qua":
                diaAtual = DiaDaSemana.QUARTA;
                break;
            case "qui":
                diaAtual = DiaDaSemana.QUINTA;
                break;
            case "sex":
                diaAtual = DiaDaSemana.SEXTA;
                break;
        }

        for (AulaDoDia dia : horario.getAulasDoDia()) {
            if (dia.getDiaDaSemana().equals(diaAtual)) {
                return dia;
            }
        }
        return null;
    }

    private Professor acharProfessor(AulaDoDia dia, LocalTime horarioInicial) {
        List<Aula> aulas = dia.getAulas();

        int c = 0;

        do {
            if (horarioInicial.isAfter(LocalTime.now())) {
                return aulas.get(c).getProfessor();
            } else {
                horarioInicial.plusMinutes(45);
            }
            c++;
        } while (c <= aulas.size());

        return aulas.getLast().getProfessor();
    }
}
