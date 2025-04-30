package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.UsuarioDto;
import com.senai.ControleDeAcessoSpring.domain.entity.*;
import com.senai.ControleDeAcessoSpring.domain.repository.UsuarioRepository;
import jakarta.persistence.DiscriminatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public boolean salvar(UsuarioDto usuarioDto){

        Usuario usuario = new Usuario()

        usuario.setId(usuarioDto.id());
        usuario.setIdAcesso(usuarioDto.idAcesso());
        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setTelefone(usuarioDto.telefone());
        usuario.setDataDeNascimento(usuarioDto.dataDeNascimento());
        usuario.setTurma(usuarioDto.turma());
        usuario.setOcorrencias(mapOcorrencias(usuarioDto.ocorrencias()));
        usuario.setJustificativas(mapJustificativas(usuarioDto.justificativas()));

        usuarioRepo.save(usuario);
        return true;
    }

    private List<Ocorrencia> mapOcorrencias(List<OcorrenciaDto> ocorrenciaDtos){
        return new ArrayList<>(ocorrenciaDtos.stream().map(ocorrenciaDto -> {
            Ocorrencia ocorrencia = new Ocorrencia();

            ocorrencia.setJustificativa(ocorrenciaDto.justificativa());
            ocorrencia.setDataEhora(ocorrenciaDto.dataEHora());
            ocorrencia.setStatus(ocorrenciaDto.status());
            ocorrencia.setTipo(ocorrenciaDto.tipoOcorrencia());
            return ocorrencia;
        }).toList()
        );
    }

    private List<Justificativa> mapJustificativas(List<JustificativaDto> justificativaDtos){
        return  new ArrayList<>(justificativaDtos.stream().map(justificativaDto -> {
            Justificativa justificativa = new Justificativa();

            justificativa.setTipo(justificativaDto.tipoJustificativa());
            justificativa.setDescricao(justificativaDto.descricao());
            justificativa.setDataDeInicio(justificativaDto.dataDeInicio());
            justificativa.setQtdDias(justificativaDto.qtdDeDias());
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
                        aluno.getOcorrencias().stream().map(ocorrencia -> new OcorrenciaDto(
                                ocorrencia.getJustificativa(),
                                ocorrencia.getDataEhora(),
                                ocorrencia.getStatus(),
                                ocorrencia.getTipo()
                        )).toList(),
                        aluno.getJustificativas().stream().map(justificativa -> new JustificativaDto(
                                justificativa.getTipo(),
                                justificativa.getDescricao(),
                                justificativa.getDataDeInicio(),
                                justificativa.getQtdDias(),
                                justificativa.getAnexo(),
                                justificativa.getStatus()
                        )).toList()
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
                aluno.getOcorrencias().stream().map(ocorrencia -> new OcorrenciaDto(
                        ocorrencia.getJustificativa(),
                        ocorrencia.getDataEhora(),
                        ocorrencia.getStatus(),
                        ocorrencia.getTipo()
                )).toList(),
                aluno.getJustificativas().stream().map(justificativa -> new JustificativaDto(
                        justificativa.getTipo(),
                        justificativa.getDescricao(),
                        justificativa.getDataDeInicio(),
                        justificativa.getQtdDias(),
                        justificativa.getAnexo(),
                        justificativa.getStatus()
                )).toList()
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
