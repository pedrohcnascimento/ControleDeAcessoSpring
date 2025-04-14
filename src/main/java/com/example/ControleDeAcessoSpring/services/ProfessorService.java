package com.example.ControleDeAcessoSpring.services;

import com.example.ControleDeAcessoSpring.model.entity.Professor;
import com.example.ControleDeAcessoSpring.model.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor create(Professor obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Professor getById(Long id) {
        Optional<Professor> obj = repository.findById(id);
        return obj.get();
    }

    public List<Professor> getAll() {
        return repository.findAll();
    }

    public Professor update(Professor obj) {
        Optional<Professor> newObj = repository.findById(obj.getId());
        updateProfessor(newObj, obj);
        return repository.save(newObj.get());
    }

    private Professor updateProfessor(Optional<Professor> newObj, Professor obj) {
        newObj.get().setNome(obj.getNome());
        newObj.get().setUnidadesCurriculares(obj.getUnidadesCurriculares());
        newObj.get().setTurma(obj.getTurma());
        newObj.get().setIdade(obj.getIdade());
        return obj;
    }
}

