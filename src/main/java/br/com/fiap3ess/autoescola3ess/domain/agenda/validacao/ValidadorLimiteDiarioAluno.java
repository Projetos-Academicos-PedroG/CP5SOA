package br.com.fiap3ess.autoescola3ess.domain.agenda.validacao;

import br.com.fiap3ess.autoescola3ess.domain.agenda.DadosAgendamento;
import br.com.fiap3ess.autoescola3ess.domain.agenda.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorLimiteDiarioAluno implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamento dados) {
        LocalDateTime inicioExpediente = dados.dataHora().withHour(6);
        LocalDateTime fimExpediente = dados.dataHora().withHour(21 - 1);

        boolean reincidencia = repository.existsByAlunoIdAndDataHoraBetween(
                dados.idAluno(),
                inicioExpediente,
                fimExpediente
        );
    }
}