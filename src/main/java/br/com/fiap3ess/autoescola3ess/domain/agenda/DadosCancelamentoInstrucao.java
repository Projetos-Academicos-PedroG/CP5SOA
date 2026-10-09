package br.com.fiap3ess.autoescola3ess.domain.agenda;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoInstrucao(
        @NotNull
        Long idInstrucao,

        @NotNull
        MotivoCancelamento motivo
) {
}