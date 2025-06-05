package com.senai.ControleDeAcessoSpring.domain.service;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AtrasoService {

    @Autowired
    private static AlunoRepository alunoRepository;

    private static Semestre definirSemetre(Aluno aluno) {
        SubTurma subturma = definirSubTurma(aluno);
        List<Semestre> semestres = subturma.getSemestres();
        LocalDate datainicial = subturma.getTurma().getDataInicial();
        LocalDate hoje = LocalDate.now();

        long mesesPassados = ChronoUnit.MONTHS.between(datainicial, hoje);

        Integer semestresPassados = Math.toIntExact(mesesPassados / 6);

        return semestres.get(semestresPassados + 1);
    }

    private static AulaDoDia definirDia(Semestre semestre) {
        List<AulaDoDia> aulasDoDia = semestre.getHorarioPadrao().getAulasDoDia();
        DiaDaSemana hoje =
                switch (LocalDate.now().getDayOfWeek()) {
                    case MONDAY -> DiaDaSemana.SEGUNDA;
                    case TUESDAY -> DiaDaSemana.TERCA;
                    case WEDNESDAY -> DiaDaSemana.QUARTA;
                    case THURSDAY -> DiaDaSemana.QUINTA;
                    case FRIDAY -> DiaDaSemana.SEXTA;
                    default -> null;
                };

        for (AulaDoDia dia : aulasDoDia) {
            if (dia.getDiaDaSemana().equals(hoje)) {
                return dia;
            }
        }

        return null;
    }

    public static Aula definirAula(Aluno aluno) {
        AulaDoDia dia = definirDia(definirSemetre(aluno));
        Aula aula = null;
        assert dia != null;
        Turma turma = dia.getHorario().getSemestre().getSubTurma().getTurma();
        int contador = 0;
        LocalTime entrada = turma.getHorarioEntrada();

        do {
            if (LocalTime.now().isAfter(entrada)) {
                aula = dia.getAulas().get(contador);
            }
            entrada = entrada.plusMinutes(turma.getCurso().getTipo().getMinutosPorAula());
            contador++;
        } while(contador <= dia.getAulas().size());

        return aula;
    }

    public static boolean definirAtraso(Aluno aluno) {
        LocalTime agora = LocalTime.now();
        LocalTime horarioDeEntrada = definirSubTurma(aluno).getTurma().getHorarioEntrada();
        Integer tolerancia = definirSubTurma(aluno).getTurma().getCurso().getToleranciaMinutos();

        return !(agora.isBefore(horarioDeEntrada.plusMinutes(tolerancia)));
    }

    private static SubTurma definirSubTurma(Aluno aluno) {
        LocalTime agora = LocalTime.now();

        if (agora.isBefore(LocalTime.of(12, 0))) {
            return filtrarHorario(aluno, Periodo.MANHA);
        } else if (agora.isBefore(LocalTime.of(17,25))){
            return filtrarHorario(aluno, Periodo.TARDE);
        } else {
            return filtrarHorario(aluno, Periodo.NOITE);
        }
    }

    private static SubTurma filtrarHorario (Aluno aluno, Periodo periodo) {
        for (SubTurma possivelSubTurma : aluno.getSubTurmas()) {
            if (possivelSubTurma.getTurma().getPeriodo().equals(periodo)) {
                return possivelSubTurma;
            }
        }

        return filtrarHorario(aluno, Periodo.INTEGRAL);
    }

    private static Aluno encontrarAluno(String idAcesso) {
        return alunoRepository.findByIdAcesso(idAcesso);
    }
}
