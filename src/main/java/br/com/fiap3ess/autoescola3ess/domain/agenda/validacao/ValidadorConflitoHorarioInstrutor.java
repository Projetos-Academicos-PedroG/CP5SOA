package br.com.fiap3ess.autoescola3ess.domain.agenda.validacao;

import br.com.fiap3ess.autoescola3ess.domain.agenda.DadosAgendamento;
import br.com.fiap3ess.autoescola3ess.domain.agenda.InstrucaoRepository;
import br.com.fiap3ess.autoescola3ess.domain.agenda.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConflitoHorarioInstrutor implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamento dados) {
        boolean instrutorOcupado = repository.existsByInstrutorIdAndDataHora(
                dados.idInstrutor(),
                dados.dataHora()
        );

        if (instrutorOcupado) {
            throw new ValidacaoException("Instrutor ocupada na data/hora escolhida!");
        }
    }
}