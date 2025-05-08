package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.CoordenadorDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.ProfessorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository coordenadorRepo;

    public boolean salvar(CoordenadorDto coordenadorDto){

        Coordenador c = new Coordenador();

        c.setIdAcesso(coordenadorDto.idAcesso());
        c.setNome(coordenadorDto.nome());
        c.setEmail(coordenadorDto.email());
        c.setTelefone(coordenadorDto.telefone());
        c.setCargo("Coordenador");
        c.setFoto(coordenadorDto.foto());
        c.setEquipeProfessores(mapEquipe(coordenadorDto.equipe()));

        coordenadorRepo.save(c);
        return true;
    }

    private List<Professor> mapEquipe(List<ProfessorDto> professorDtos){
        return new ArrayList<>(professorDtos.stream().map(professorDto -> {
            Professor professor = new Professor();

            professor.setIdAcesso(professorDto.idAcesso());
            professor.setNome(professorDto.nome());
            professor.setEmail(professorDto.email());
            professor.setTelefone(professorDto.telefone());
            professor.setCargo("Professor");
            professor.setTurma(professorDto.turma());
            professor.setCursos(new ArrayList<>(professorDto.cursos()));
            professor.setFoto(professorDto.foto());
            return professor;
        }).toList());
    }

    public List<CoordenadorDto> listar(){
        return coordenadorRepo.findAll().stream().map(c -> new CoordenadorDto(
                c.getId(),
                c.getIdAcesso(),
                c.getNome(),
                c.getEmail(),
                c.getTelefone(),
                c.getCargo(),
                c.getFoto(),
                c.getEquipeProfessores().stream().map(p -> new ProfessorDto(
                        p.getId(),
                        p.getIdAcesso(),
                        p.getNome(),
                        p.getEmail(),
                        p.getTelefone(),
                        p.getCargo(),
                        p.getTurma(),
                        p.getCursos(),
                        p.getFoto()
                )).toList()
        )).toList();
    }

    public Optional<CoordenadorDto> buscarPorId(Long id){
        return coordenadorRepo.findById(id).map(coordenador -> new CoordenadorDto(
                        coordenador.getId(),
                        coordenador.getIdAcesso(),
                        coordenador.getNome(),
                        coordenador.getEmail(),
                        coordenador.getTelefone(),
                        coordenador.getCargo(),
                        coordenador.getFoto(),
                        coordenador.getEquipeProfessores().stream().map(
                                professor -> new ProfessorDto(
                                       professor.getId(),
                                        professor.getIdAcesso(),
                                        professor.getNome(),
                                        professor.getEmail(),
                                        professor.getTelefone(),
                                        professor.getCargo(),
                                        professor.getTurma(),
                                        professor.getCursos(),
                                        professor.getFoto()
                                )
                        ).toList()
                )
        );
    }

    public boolean atualizar(Long id, CoordenadorDto coordenadorDto){


        return coordenadorRepo.findById(id).map(coordenador -> {
            coordenador.setIdAcesso(coordenadorDto.idAcesso());
            coordenador.setNome(coordenadorDto.nome());
            coordenador.setEmail(coordenadorDto.email());
            coordenador.setTelefone(coordenadorDto.telefone());
            coordenador.setFoto(coordenadorDto.foto());
            coordenador.setEquipeProfessores(mapEquipe(coordenadorDto.equipe()));
            coordenadorRepo.save(coordenador);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id){
        if (coordenadorRepo.existsById(id)){
            coordenadorRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private boolean validarEquipe(List<ProfessorDto> equipe) {
        return equipe != null && equipe.size() >= 3;
    }
}

