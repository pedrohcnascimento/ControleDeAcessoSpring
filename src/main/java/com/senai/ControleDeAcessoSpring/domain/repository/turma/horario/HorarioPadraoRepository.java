package com.senai.ControleDeAcessoSpring.domain.repository.turma.horario;

import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.HorarioPadrao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioPadraoRepository extends JpaRepository<HorarioPadrao, Long> {
}
