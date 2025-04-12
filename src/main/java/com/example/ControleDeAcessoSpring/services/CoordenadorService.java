package com.example.ControleDeAcessoSpring.services;

import com.example.ControleDeAcessoSpring.model.entity.Coordenador;
import com.example.ControleDeAcessoSpring.model.repositories.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;

    public Coordenador create(Coordenador obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Coordenador getById(Long id) {
        Optional<Coordenador> obj = repository.findById(id);
        return obj.get();
    }

    public List<Coordenador> getAll() {
        return repository.findAll();
    }

    public Coordenador update(Coordenador obj) {
        Optional<Coordenador> newObj = repository.findById(obj.getId());
        updateAluno(newObj, obj);
        return repository.save(newObj.get());
    }

    private Coordenador updateAluno(Optional<Coordenador> newObj, Coordenador obj) {
        newObj.get().setNome(obj.getNome());
        newObj.get().setListaDeProfessores(obj.getListaDeProfessores());
        newObj.get().setIdade(obj.getIdade());
        return obj;
    }
}

