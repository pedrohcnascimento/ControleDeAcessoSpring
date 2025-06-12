package com.senai.ControleDeAcessoSpring.aplication.service.usuarios.aluno;

import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.AlunoDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.JustificativaDto;
import com.senai.ControleDeAcessoSpring.aplication.dto.usuarios.aluno.OcorrenciaDto;
import com.senai.ControleDeAcessoSpring.domain.entity.curso.UnidadeCurricular;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.Professor;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Aluno;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Justificativa;
import com.senai.ControleDeAcessoSpring.domain.entity.usuarios.aluno.Ocorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaJustificativa;
import com.senai.ControleDeAcessoSpring.domain.enums.StatusDaOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.enums.TipoDeOcorrencia;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.AlunoRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.JustificativaRepository;
import com.senai.ControleDeAcessoSpring.domain.repository.usuarios.aluno.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    private JustificativaRepository justificativaRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;


    public void cadastrarAluno(List<AlunoDto> listaDtos) {
        listaDtos.forEach(alunoDto -> {
            alunoRepository.save(alunoDto.fromDTO());
        });
    }

    public List<AlunoDto> listarAtivos() {
        return alunoRepository.findByAtivoTrue()
                .stream().map(AlunoDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AlunoDto> buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .filter(Aluno::getAtivo)
                .map(AlunoDto::toDTO);
    }

    public boolean atualizar(Long id, AlunoDto dto) {
        return alunoRepository.findById(id).map(aluno -> {
            Aluno alunoAtualizado = dto.fromDTO();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setCpf(alunoAtualizado.getCpf());
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    public boolean inativar(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }

    // Justificativas
    public List<JustificativaDto> listarJustificativas(Long id) {
        return alunoRepository.findById(id).get().getJustificativas().stream().filter(justificativa -> {
            if (justificativa.getStatus().equals(StatusDaJustificativa.INATIVADA)) {
                return false;
            } else return true;
        }) .map(JustificativaDto::toDto).toList();
    }

    public Optional<JustificativaDto> listarJustificativaPorId(Long idJustificativa) {
        return justificativaRepository.findById(idJustificativa).map(JustificativaDto::toDto);
    }

    public boolean criarJustificativa(Long id, JustificativaDto justificativaDto) {
        Justificativa j = justificativaDto.fromDto();
        j.setAluno(alunoRepository.findById(id).get());
        j.setStatus(StatusDaJustificativa.AGUARDANDO_ANALISE);
        j.setDataHoraCriacao(LocalDateTime.now()); // Hora em que é cadastrada
        j.setDataHoraConclusao(null); // Ainda não foi concluída
        return alunoRepository.findById(id).map(aluno -> {
            aluno.getJustificativas().add(j);
            justificativaRepository.save(j);
            return true;
        }).orElse(false);
    }

    public boolean alterarStatusJustificativa(Long idJustificativa, StatusDaJustificativa status) {
        justificativaRepository.findById(idJustificativa).map(justificativa -> {
                    justificativa.setStatus(status);
                    if (status.equals(StatusDaJustificativa.APROVADA) || status.equals(StatusDaJustificativa.REPROVADA)) {
                        justificativa.setDataHoraConclusao(LocalDateTime.now());
                    }
                    justificativaRepository.save(justificativa);
                    return true;
                }
        );
        return false;
    }

    public boolean inativarJustificativa(Long idJustificativa) {
        justificativaRepository.findById(idJustificativa).map(justificativa -> {
            justificativa.setStatus(StatusDaJustificativa.INATIVADA);
            justificativaRepository.save(justificativa);
            return true;
        });
        return false;
    }

    // Ocorrência Saída
    public List<OcorrenciaDto> listarOcorrencias(Long id) {
        return alunoRepository.findById(id).get().getOcorrencias().stream().filter(ocorrencia -> {
            if (ocorrencia.getStatus().equals(StatusDaOcorrencia.INATIVADA)) {
                return false;
            } else return true;
        }) .map(OcorrenciaDto::toDto).toList();
    }

    public Optional<OcorrenciaDto> listarOcorrenciaPorId(Long idOcorrencia) {
        return ocorrenciaRepository.findById(idOcorrencia).map(OcorrenciaDto::toDto);
    }

    public boolean criarOcorrenciaSaida(Long id, OcorrenciaDto ocorrenciaDto) {
        Ocorrencia o = ocorrenciaDto.fromDto();
        o.setAluno(alunoRepository.findById(id).get());
        o.setStatus(StatusDaOcorrencia.AGUARDANDO_AUTORIZACAO);
        o.setTipo(TipoDeOcorrencia.SAIDA_ANTECIPADA); // Só pode ser de saída
        o.setDataHoraCriacao(LocalDateTime.now()); // Hora em que é cadastrada
        o.setDataHoraConclusao(null); // Ainda não foi concluída
        Professor professor = new Professor(); professor.setId(2l); o.setProfessorResponsavel(professor);
        UnidadeCurricular uc = new UnidadeCurricular(); uc.setId(1l); o.setUnidadeCurricular(uc);
        // Ver a questão do horário solicitado para saída antecipada e se precisa de prof e uc
        return alunoRepository.findById(id).map(aluno -> {
            aluno.getOcorrencias().add(o);
            ocorrenciaRepository.save(o);
            return true;
        }).orElse(false);
    }

    public boolean alterarStatusOcorrencia(Long idOcorrencia, StatusDaOcorrencia status) {
        ocorrenciaRepository.findById(idOcorrencia).map(ocorrencia -> {
                    ocorrencia.setStatus(status);
                    if (status.equals(StatusDaOcorrencia.APROVADO) || status.equals(StatusDaOcorrencia.REPROVADO)) {
                        ocorrencia.setDataHoraConclusao(LocalDateTime.now());
                    }
                    ocorrenciaRepository.save(ocorrencia);
                    return true;
                }
        );
        return false;
    }

    public boolean inativarOcorrencia(Long idOcorrencia) {
        ocorrenciaRepository.findById(idOcorrencia).map(ocorrencia -> {
            ocorrencia.setStatus(StatusDaOcorrencia.INATIVADA);
            ocorrenciaRepository.save(ocorrencia);
            return true;
        });
        return false;
    }
}
