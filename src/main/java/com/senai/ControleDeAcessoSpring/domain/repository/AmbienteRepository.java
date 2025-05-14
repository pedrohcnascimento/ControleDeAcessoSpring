package com.senai.ControleDeAcessoSpring.domain.repository;

import com.seusistema.controleacesso.ambiente.model.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

    List<Ambiente> findByAtivoTrue();

    Optional<Ambiente> findByIdAndAtivoTrue(Long id);
}
