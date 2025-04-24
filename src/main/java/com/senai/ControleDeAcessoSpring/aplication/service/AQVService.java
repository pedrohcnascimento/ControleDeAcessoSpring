package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.AQVDto;
import com.senai.ControleDeAcessoSpring.domain.Entity.AQV;
import com.senai.ControleDeAcessoSpring.domain.Repository.AQVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AQVService {

    @Autowired
    private AQVRepository aqvRepository;

    public boolean salvar(AQVDto aqvDto){

        AQV aqv = new AQV();

        aqv.setIdAcesso(aqvDto.idAcesso());
        aqv.setNome(aqvDto.nome());
        aqv.setEmail(aqvDto.email());
        aqv.setTelefone(aqvDto.telefone());
        aqv.setDataDeNascimento(aqvDto.dataDeNascimento());
        return true;
    }

    public List<AQVDto> listar(){
        return aqvRepository.findAll().stream().map( aqv -> new AQVDto(
               aqv.getId(),
                aqv.getIdAcesso(),
                aqv.getNome(),
                aqv.getEmail(),
                aqv.getTelefone(),
                aqv.getDataDeNascimento()
            )
        ).toList();
    }

    public Optional<AQVDto> buscarPorId(Long id){
        return aqvRepository.findById(id).map(aqv -> new AQVDto(
                aqv.getId(),
                aqv.getIdAcesso(),
                aqv.getNome(),
                aqv.getEmail(),
                aqv.getTelefone(),
                aqv.getDataDeNascimento()
        ));
    }

    public boolean atualizar(Long id, AQVDto aqvDto){


        return aqvRepository.findById(id).map( aqv -> {
            aqv.setIdAcesso(aqvDto.idAcesso());
            aqv.setNome(aqvDto.nome());
            aqv.setEmail(aqvDto.email());
            aqv.setTelefone(aqvDto.telefone());
            aqv.setDataDeNascimento(aqvDto.dataDeNascimento());

            aqvRepository.save(aqv);
            return true;
        }).orElse(false);
    }

    public boolean deletar(Long id){
        if (aqvRepository.existsById(id)){
            aqvRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
