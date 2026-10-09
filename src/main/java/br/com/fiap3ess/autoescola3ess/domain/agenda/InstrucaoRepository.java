package br.com.fiap3ess.autoescola3ess.domain.agenda;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface InstrucaoRepository extends JpaRepository<Instrucao, Long> {
    boolean existsByInstrutorIdAndDataHora(Long idInstrutor, LocalDateTime dataHora);

    boolean existsByAlunoIdAndDataHoraBetween(Long idAluno, LocalDateTime inicioExpediente, LocalDateTime fimExpediente);
}