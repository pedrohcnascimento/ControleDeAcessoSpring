package com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findByStatusOrStatusOrStatusOrStatus(StatusDaOcorrencia status1, StatusDaOcorrencia status2, StatusDaOcorrencia status3, StatusDaOcorrencia status4);
}
