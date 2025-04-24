package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AlunoDto;
import com.senai.ControleDeAcessoSpring.domain.Entity.Aluno;
import com.senai.ControleDeAcessoSpring.domain.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        aluno.setCargo("Aluno");
        aluno.setFoto(alunoDto.foto());

        alunoRepo.save(aluno);
        return true
    }
}
