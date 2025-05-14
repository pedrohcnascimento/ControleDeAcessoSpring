package com.senai.ControleDeAcessoSpring.aplication.service;

import com.senai.ControleDeAcessoSpring.aplication.dto.TurmaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    public void cadastrarTurma(TurmaDto turmaDto){
        Turma turma = new Turma();

        turma.setNome(turmaDto.nome());
        turma.setPeriodo(turmaDto.periodo());
        turma.setDataInicial(turmaDto.dataInicial());
        turma.setHorarioEntrada(turmaDto.horarioEntrada());
        turma.setQtdSemestres(turmaDto.qtdSemestres());
        turma.setQtdAulasporDia(turmaDto.aulasPorDia());
        turma.setSubTurmas(new ArrayList<>());

        turmaRepository.save(turma);
    }

    public List<TurmaDto> listarTurmas(){
        return turmaRepository.findAll()
                .stream()
                .map(turma -> new TurmaDto(
                        turma.getId(),
                        turma.getNome(),
                        turma.getPeriodo(),
                        turma.getDataInicial(),
                        turma.getHorarioEntrada(),
                        turma.getQtdSemestres(),
                        turma.getQtdAulasporDia()
                )).toList();
    }

    public Optional<TurmaDto> buscarTurmaPorId(Long id){
        return turmaRepository.findById(id)
                .map(turma -> new TurmaDto(
                        turma.getId(),
                        turma.getNome(),
                        turma.getPeriodo(),
                        turma.getDataInicial(),
                        turma.getHorarioEntrada(),
                        turma.getQtdSemestres(),
                        turma.getQtdAulasporDia()
                ));
    }

    public boolean atualizarTurma(Long id, TurmaDto turmaDto){
        return turmaRepository.findById(id).map(turma -> {
            turma.setNome(turmaDto.nome());
            turma.setPeriodo(turmaDto.periodo());
            turma.setDataInicial(turmaDto.dataInicial());
            turma.setHorarioEntrada(turmaDto.horarioEntrada());
            turma.setQtdSemestres(turmaDto.qtdSemestres());
            turma.setQtdAulasporDia(turmaDto.aulasPorDia());
            return true;
        }).orElse(false);
    }



    public boolean deletarTurma(Long id){
        if(turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}