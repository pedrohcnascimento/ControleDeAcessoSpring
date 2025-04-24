package com.senai.ControleDeAcessoSpring.aplication.dto;

import java.util.Date;
import java.util.List;

public record AlunoDto(Long id,
                       Long idAcesso,
                       String nome,
                       String email,
                       String telefone,
                       Date dataDeNascimento,
                       TurmaDto turma,
                       List<OcorrenciaDto> ocorrencias,
                       List<JustificativaDto> justificativas) {
}
