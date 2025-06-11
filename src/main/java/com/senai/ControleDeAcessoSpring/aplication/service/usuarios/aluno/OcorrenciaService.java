package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.SubTurma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.Turma;
import com.senai.ControleDeAcessoSpring.domain.entity.turma.horario.Aula;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Usuario;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.Periodo;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.UsuarioRepository;
import com.senai.ControleDeAcessoSpring.domain.service.AtrasoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrarOcorrencia(OcorrenciaDto dto) {
        Ocorrencia ocorrencia = dto.fromDto();
        ocorrencia.setAtivo(true);
        ocorrencia.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        ocorrencia.setDataHoraCriacao(LocalDateTime.now());
        ocorrencia.setDataHoraConclusao(null);
        ocorrencia.setAluno(null);
        ocorrencia.setProfessorResponsavel(null);
        ocorrencia.setUnidadeCurricular(null);
        ocorrenciaRepository.save(ocorrencia);
    }

    @Transactional
    public List<OcorrenciaDto> listar() {
        return ocorrenciaRepository.findByAtivoTrue()
                .stream().map(OcorrenciaDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<OcorrenciaDto> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).map(OcorrenciaDto::toDto);
    }

    @Transactional
    public boolean inativar(Long id) {
        return ocorrenciaRepository.findById(id).map(ocorrencia -> {
            ocorrencia.setAtivo(false);
            ocorrenciaRepository.save(ocorrencia);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean alterarStatus(Long id, StatusDaOcorrencia status) {
        ocorrenciaRepository.findById(id).map(ocorrencia -> {
                    ocorrencia.setStatus(status);
                    if (status.equals(StatusDaOcorrencia.APROVADO) || status.equals(StatusDaOcorrencia.REPROVADO)) {
                        ocorrencia.setDataHoraConclusao(LocalDateTime.now());
                    }
                    ocorrenciaRepository.save(ocorrencia);
                    return true;
                }
        ).orElse(false);
        return false;
    }

    @Transactional
    public void criarOcorrenciaDeAcesso(String idAcesso) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAcesso(idAcesso);
        if (usuarioOpt.isPresent()) {
            System.out.println("O usuário existe!");
            if (usuarioOpt.get() instanceof Aluno aluno) {
                Turma turma = aluno.getSubTurmas().stream().filter(st -> {
                    if (st.getTurma().getPeriodo().equals(Periodo.INTEGRAL)) {
                        return true;
                    }

                    LocalTime horarioDeEntradaComTolerancia = st.getTurma().getHorarioEntrada()
                            .plusMinutes(st.getTurma().getCurso().getToleranciaMinutos());

                    int tempoDeAula =
                            st.getTurma().getCurso().getTipo().getMinutosPorAula() +
                            st.getTurma().getQtdAulasporDia();
                    if (LocalTime.now().isAfter(horarioDeEntradaComTolerancia) &&
                        LocalTime.now().isBefore(horarioDeEntradaComTolerancia.plusMinutes(tempoDeAula))) {
                        return true;
                    }

                    return false;
                }).findFirst().get().getTurma();
//                // Verificando se o aluno chegou atrasado
//                if (LocalTime.now().isAfter(entradaComTolerancia)) {
//                    System.out.println(aluno.getNome() + " está atrasado!");
//
//                    // Criando a ocorrência de atraso
//                    Ocorrencia ocorrencia = new Ocorrencia(
//                            null,  // ID será gerado automaticamente pelo banco de dados
//                            TipoDeOcorrencia.ATRASO,  // Tipo da ocorrência
//                            "Aluno chegou atrasado",  // Descrição do motivo da ocorrência
//                            StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO,  // Status inicial
//                            LocalDateTime.now(),  // Data e hora da criação
//                            null,  // Data e hora de conclusão (ainda não definida)
//                            aluno,  // Associando o aluno à ocorrência
//                            aula.getProfessor(),  // Associando o professor responsável
//                            aula.getUnidadeCurricular(),  // Associando a unidade curricular
//                            true  // Indicando que a ocorrência está ativa
//                    );
//                    ocorrenciaRepository.save(ocorrencia);  // Salvando a ocorrência no banco
//                } else {
//                    System.out.println(aluno.getNome() + " não está atrasado.");
//                }
            }
        } else {
            System.out.println("O usuário não existe!");
        }
    }
}

