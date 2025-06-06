package com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;

import java.time.LocalDateTime;
import java.util.Optional;

public record OcorrenciaDto(
        Long id,
        TipoDeOcorrencia tipo,
        String descricao,
        StatusDaOcorrencia status,
        LocalDateTime dataHoraCriacao,
        LocalDateTime dataHoraConclusao,
        Long alunoId,
        Long professorResponsavelId,
        Long unidadeCurricularId

) {
    public static OcorrenciaDto toDto(Ocorrencia o){
        return new OcorrenciaDto(
                o.getId(),
                o.getTipo(),
                o.getDescricao(),
                o.getStatus(),
                o.getDataHoraCriacao(),
                o.getDataHoraConclusao(),
                o.getAluno().getId(),
                o.getProfessorResponsavel().getId(),
                o.getUnidadeCurricular().getId()
        );
    }

    public Ocorrencia fromDto(Optional<Aluno> byId, Professor professor, UnidadeCurricular unidadeCurricular){
        Ocorrencia o = new Ocorrencia();
        o.setId(id);
        o.setTipo(tipo);
        o.setDescricao(descricao);
        o.setStatus(status);
        o.setDataHoraCriacao(dataHoraCriacao);
        o.setDataHoraConclusao(dataHoraConclusao);
        o.setAluno(null);
        o.setProfessorResponsavel(null);
        o.setUnidadeCurricular(null);
        return o;
    }

    public Ocorrencia fromDto(Aluno aluno, Professor professor, UnidadeCurricular unidadeCurricular){
        Ocorrencia o = new Ocorrencia();
        o.setId(id);
        o.setTipo(tipo);
        o.setDescricao(descricao);
        o.setStatus(status);
        o.setDataHoraCriacao(dataHoraCriacao);
        o.setDataHoraConclusao(dataHoraConclusao);
        o.setAluno(aluno);
        o.setProfessorResponsavel(professor);
        o.setUnidadeCurricular(unidadeCurricular);
        return o;
    }
}