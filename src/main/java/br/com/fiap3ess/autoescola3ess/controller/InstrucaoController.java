package br.com.fiap3ess.autoescola3ess.controller;

import br.com.fiap3ess.autoescola3ess.domain.agenda.DadosAgendamento;
import br.com.fiap3ess.autoescola3ess.domain.agenda.DadosCancelamentoInstrucao;
import br.com.fiap3ess.autoescola3ess.domain.agenda.DadosDetalhamentoAgendamento;
import br.com.fiap3ess.autoescola3ess.service.AgendaDeInstrucoes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgendamento> agendarInstrucao(@RequestBody @Valid DadosAgendamento dados) {
        return ResponseEntity.ok(agenda.agendar(dados));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoInstrucao dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}