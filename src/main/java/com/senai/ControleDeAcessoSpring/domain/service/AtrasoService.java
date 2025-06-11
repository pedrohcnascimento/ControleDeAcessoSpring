package com.senai.ControleDeAcessoSpring.domain.service;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.Semestre;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.AulaDoDia;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.enums.DiaDaSemana;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import com.senai.ControleDeAcessoSpring.domain.repository.turma.SubTurmaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtrasoService {

    @Autowired
    private static AlunoRepository alunoRepository;

    @Autowired
    private static SubTurmaRepository subTurmaRepository;

//    private static Semestre definirSemetre(Aluno aluno) {
//        /*SubTurma subturma = (aluno);
//        List<Semestre> semestres = subturma.getSemestres();
//        LocalDate datainicial = subturma.getTurma().getDataInicial();
//        LocalDate hoje = LocalDate.now();
//        */
//        long mesesPassados = ChronoUnit.MONTHS.between(datainicial, hoje);
//
//        Integer semestresPassados = Math.toIntExact(mesesPassados / 6);
//
//        return semestres.get(semestresPassados + 1);
//    }

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
//
//    public static Aula definirAula(Aluno aluno) {
//        AulaDoDia dia = definirDia(definirSemetre(aluno));
//        Aula aula = null;
//        assert dia != null;
//        Turma turma = dia.getHorario().getSemestre().getSubTurma().getTurma();
//        int contador = 0;
//        LocalTime entrada = turma.getHorarioEntrada();
//
//        do {
//            if (LocalTime.now().isAfter(entrada)) {
//                aula = dia.getAulas().get(contador);
//            }
//            entrada = entrada.plusMinutes(turma.getCurso().getTipo().getMinutosPorAula());
//            contador++;
//        } while(contador <= dia.getAulas().size());
//
//        return aula;
//    }

    @Transactional
    public static boolean definirAtraso(Aluno aluno) {
        LocalTime agora = LocalTime.now();
        System.out.println("Horario atual: " + agora);

        Periodo periodoAtual = agora.isAfter(LocalTime.of(12, 0)) ? Periodo.MANHA : Periodo.TARDE;
        System.out.println("periodo: " + periodoAtual);

        List<SubTurma> subTurmasDoAluno = aluno.getSubTurmas();
        for (SubTurma st : subTurmasDoAluno) {
            System.out.println("- " + st.getNome());
        }

        SubTurma subTurmaDoAluno = null;
        for (SubTurma subTurma : subTurmasDoAluno) {
            if (subTurma.getTurma().getPeriodo().equals(periodoAtual)) {
                subTurmaDoAluno = subTurma;
                break; // Se encontramos a subturma correspondente ao período, saímos do loop
            }
        }

        // Se não encontrou a subturma correspondente ao período, podemos retornar false ou lançar uma exceção
        if (subTurmaDoAluno == null) {
            System.out.println("Aluno não pertence a uma subturma no período " + periodoAtual);
            return false;  // ou lançar uma exceção, dependendo do comportamento esperado
        }

        // Exibir informações sobre a subturma do aluno
        System.out.println("Subturma: " + subTurmaDoAluno.getNome());

        Turma turma = subTurmaDoAluno.getTurma();
        System.out.println("Aluno pertence a turma: " + turma.getSiglaDaTurma());

        LocalTime horarioDeEntrada = turma.getHorarioEntrada();
        System.out.println("Horario de entrada: " + horarioDeEntrada);

        Integer tolerancia = turma.getCurso().getToleranciaMinutos();
        System.out.println("Com tolerância de " + tolerancia + "minutos");

        return !(agora.isBefore(horarioDeEntrada.plusMinutes(tolerancia)));
    }



    @Transactional
    private static Aluno encontrarAluno(String idAcesso) {
        return alunoRepository.findByIdAcesso(idAcesso);
    }
}
