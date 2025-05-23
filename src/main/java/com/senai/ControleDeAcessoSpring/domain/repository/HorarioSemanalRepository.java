package com.senai.ControleDeAcessoSpring.domain.repository;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioSemanal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioSemanalRepository extends JpaRepository<HorarioSemanal, Long> {
}
