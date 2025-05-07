package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.ProfessorDto;
import com.senai.ControleDeAcessoSpring.domain.Entity.Professor;
import com.senai.ControleDeAcessoSpring.domain.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepo;

    public boolean salvar(ProfessorDto professorDto){

        Professor professor = new Professor();

        professor.setIdAcesso(professorDto.idAcesso());
        professor.setNome(professorDto.nome());
        professor.setEmail(professorDto.email());
        professor.setTelefone(professorDto.telefone());
        professor.setCargo("Professor");
        professor.setTurma(professorDto.turma());
        professor.setCursos(professorDto.cursos());
        professor.setFoto(professorDto.foto());

        professorRepo.save(professor);
        return true;
    }

    public List<ProfessorDto> listar(){
        return professorRepo.findAll().stream().map(professor -> new ProfessorDto(
                        professor.getId(),
                        professor.getIdAcesso(),
                        professor.getNome(),
                        professor.getEmail(),
                        professor.getTelefone(),
                        professor.getCargo(),
                        professor.getTurma(),
                        professor.getFoto(),
                        professor.getCursos()
                )
        ).toList();
    }

    public Optional<ProfessorDto> buscarPorId(Long id){
        return professorRepo.findById(id).map(professor -> new ProfessorDto(
                professor.getId(),
                professor.getIdAcesso(),
                professor.getNome(),
                professor.getEmail(),
                professor.getTelefone(),
                professor.getCargo(),
                professor.getTurma(),
                professor.getFoto(),
                professor.getCursos()
        ));
    }

    public boolean atualizar(Long id, ProfessorDto professorDto){

        return professorRepo.findById(id).map(professor -> {
            professor.setIdAcesso(professorDto.idAcesso());
            professor.setNome(professorDto.nome());
            professor.setEmail(professorDto.email());
            professor.setTelefone(professorDto.telefone());
            professor.setTurma(professorDto.turma());
            professor.setCursos(professorDto.cursos());
            professor.setFoto(professorDto.foto());

            professorRepo.save(professor);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id){
        if (professorRepo.existsById(id)){
            professorRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
