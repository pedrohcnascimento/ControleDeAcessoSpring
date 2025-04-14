package com.example.ControleDeAcessoSpring.services;

import com.example.ControleDeAcessoSpring.model.entity.Acesso;
import com.example.ControleDeAcessoSpring.model.repositories.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository repository;

    public Acesso create(Acesso obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Acesso getById(Long id) {
        Optional<Acesso> obj = repository.findById(id);
        return obj.get();
    }

    public List<Acesso> getAll() {
        return repository.findAll();
    }

    public Acesso update(Acesso obj) {
        Optional<Acesso> newObj = repository.findById(obj.getId());
        updateAcesso(newObj, obj);
        return repository.save(newObj.get());
    }

    private Acesso updateAcesso(Optional<Acesso> newObj, Acesso obj) {
        newObj.get().setData(obj.getData());
        return obj;
    }
}
