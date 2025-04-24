package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.Entity.Aluno;
import com.senai.ControleDeAcessoSpring.domain.Entity.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.Entity.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepo;

    public boolean salvar(AlunoDto alunoDto){

        Aluno aluno = new Aluno();

        aluno.setId(alunoDto.id());
        aluno.setIdAcesso(alunoDto.idAcesso());
        aluno.setNome(alunoDto.nome());
        aluno.setEmail(alunoDto.email());
        aluno.setTelefone(alunoDto.telefone());
        aluno.setDataDeNascimento(alunoDto.dataDeNascimento());
        aluno.setTurma(alunoDto.turma());
        aluno.setOcorrencias(mapOcorrencias(alunoDto.ocorrencias()));
        aluno.setJustificativas(mapJustificativas(alunoDto.justificativas()));

        alunoRepo.save(aluno);
        return true;
    }

    private List<Ocorrencia> mapOcorrencias(List<OcorrenciaDto> ocorrenciaDtos){
        return new ArrayList<>(ocorrenciaDtos.stream().map(ocorrenciaDto -> {
            Ocorrencia ocorrencia = new Ocorrencia();

            ocorrencia.setJustificativa(ocorrenciaDto.justificativa());
            ocorrencia.setDataEHora(ocorrenciaDto.dataEHora());
            ocorrencia.setStatusJustificativa(ocorrenciaDto.status());
            ocorrencia.setTipoOcorrencia(ocorrenciaDto.tipoOcorrencia());
            return ocorrencia;
        }).toList()
        );
    }

    private List<Justificativa> mapJustificativas(List<JustificativaDto> justificativaDtos){
        return  new ArrayList<>(justificativaDtos.stream().map(justificativaDto -> {
            Justificativa justificativa = new Justificativa();

            justificativa.setTipoJustificativa(justificativaDto.tipoJustificativa());
            justificativa.setDescricao(justificativaDto.descricao());
            justificativa.setDataDeInicio(justificativaDto.dataDeInicio());
            justificativa.setQtdDeDias(justificativaDto.qtdDeDias());
            justificativa.setAnexo(justificativaDto.anexo());
            justificativa.setStatus(justificativaDto.status());
            return justificativa;
        }).toList()
        );
    }

    public List<AlunoDto> listar(){
        return alunoRepo.findAll().stream().map( aluno -> new AlunoDto(
                        aluno.getId(),
                        aluno.getIdAcesso(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        aluno.getTelefone(),
                        aluno.getDataDeNascimento(),
                        aluno.getTurma(),
                        aluno.getOcorrencias(),
                        aluno.getJustificativas()
                )
        ).toList();
    }

    public Optional<AlunoDto> buscarPorId(Long id){
        return alunoRepo.findById(id).map(aluno -> new AlunoDto(
                aluno.getId(),
                aluno.getIdAcesso(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getDataDeNascimento(),
                aluno.getTurma(),
                aluno.getOcorrencias(),
                aluno.getJustificativas()
        ));
    }

    public boolean atualizar(Long id, AlunoDto alunoDto){


        return alunoRepo.findById(id).map(aluno -> {
            aluno.setIdAcesso(alunoDto.idAcesso());
            aluno.setNome(alunoDto.nome());
            aluno.setEmail(alunoDto.email());
            aluno.setTelefone(alunoDto.telefone());
            aluno.setDataDeNascimento(alunoDto.dataDeNascimento());
            aluno.setTurma(alunoDto.turma());
            aluno.setOcorrencias(mapOcorrencias(alunoDto.ocorrencias()));
            aluno.setJustificativas(mapJustificativas(alunoDto.justificativas()));


            alunoRepo.save(aluno);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id){
        if (alunoRepo.existsById(id)){
            alunoRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
