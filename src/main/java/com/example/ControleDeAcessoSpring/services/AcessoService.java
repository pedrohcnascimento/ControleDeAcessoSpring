package com.example.ControleDeAcessoSpring.services;

import com.example.ControleDeAcessoSpring.model.dto.AcessoDto;
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

    public AcessoDto create(AcessoDto obj) {
        if (obj.data() != null) {
            Acesso acess = new Acesso();
            acess.setId(obj.id());
            acess.setData(obj.data());
            return obj;
        }
    }

    public boolean delete(Long id) {
        if (repository.findById(id) != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public AcessoDto getById(Long id) {
        Acesso obj = repository.findById(id).get();
        AcessoDto objDto = new AcessoDto(obj.getId(), obj.getData());
        return objDto;
    }

    public List<AcessoDto> getAll() {
        return repository.findAll().stream().map(acess -> new AcessoDto(
                acess.getId(),
                acess.getData()
        )).toList();
    }

    public AcessoDto update(Acesso obj) {
        Optional<Acesso> newObj = repository.findById(obj.getId());
        updateAcesso(newObj, obj);
        return repository.save(newObj.get());
    }

    private AcessoDto updateAcesso(Optional<AcessoDto> newObj, AcessoDto obj) {
        newObj.get().setData(obj.getData());
        return obj;
    }
}
