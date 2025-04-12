package com.example.ControleDeAcessoSpring.services;

import com.example.ControleDeAcessoSpring.model.entity.Aluno;
import com.example.ControleDeAcessoSpring.model.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public Aluno create(Aluno obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Aluno getById(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        return obj.get();
    }

    public List<Aluno> getAll() {
        return repository.findAll();
    }

    public Aluno update(Aluno obj) {
        Optional<Aluno> newObj = repository.findById(obj.getId());
        updateAluno(newObj, obj);
        return repository.save(newObj.get());
    }

    private Aluno updateAluno(Optional<Aluno> newObj, Aluno obj) {
        newObj.get().setNome(obj.getNome());
        newObj.get().setTurma(obj.getTurma());
        newObj.get().setIdade(obj.getIdade());
        return obj;
    }
}
