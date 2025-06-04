package com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JustificativaRepository extends JpaRepository<Justificativa, Long> {
    List<Justificativa> findByStatusOrStatusOrStatus(StatusDaJustificativa status1, StatusDaJustificativa status2, StatusDaJustificativa status3);
    List<Justificativa> findByAlunoAndStatusOrStatusOrStatus(Aluno aluno, StatusDaJustificativa status1, StatusDaJustificativa status2, StatusDaJustificativa status3);
}
