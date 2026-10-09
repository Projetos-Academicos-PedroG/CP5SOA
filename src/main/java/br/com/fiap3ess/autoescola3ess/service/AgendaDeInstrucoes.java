package br.com.fiap3ess.autoescola3ess.service;

import br.com.fiap3ess.autoescola3ess.domain.agenda.*;
import br.com.fiap3ess.autoescola3ess.domain.agenda.validacao.ValidadorAgendamento;
import br.com.fiap3ess.autoescola3ess.domain.Aluno;
import br.com.fiap3ess.autoescola3ess.domain.AlunoNotFoundException;
import br.com.fiap3ess.autoescola3ess.domain.AlunoRepository;
import br.com.fiap3ess.autoescola3ess.domain.instrutor.Instrutor;
import br.com.fiap3ess.autoescola3ess.domain.instrutor.InstrutorNotFoundException;
import br.com.fiap3ess.autoescola3ess.domain.instrutor.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendaDeInstrucoes {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private InstrucaoRepository repository;

    @Autowired
    private List<ValidadorAgendamento> validadoresAgendamento;

    @Transactional
    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNotFoundException("ID do aluno informado não existe!");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNotFoundException("ID do instrutor informado não existe!");
        }
        validadoresAgendamento.forEach(validador -> validador.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());

        Instrutor instrutor = escolherInstrutor(dados);
        if (instrutor == null) {
            throw new ValidacaoException("Nenhum instrutor disponível para a data/hora informada!");
        }

        Instrucao instrucao = new Instrucao(null, aluno, instrutor, dados.dataHora(), null);
        Instrucao salva = repository.save(instrucao);
        return new DadosDetalhamentoAgendamento(salva);
    }

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é campo obrigatório, caso o instrutor não seja informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(
                dados.especialidade(),
                dados.dataHora()
        );
    }

    @Transactional
    public void cancelar(DadosCancelamentoInstrucao dados) {
        if (!repository.existsById(dados.idInstrucao())) {
            throw new ValidacaoException("Id da instrução informado não existe!");
        }

        var instrucao = repository.getReferenceById(dados.idInstrucao());

        var agora = java.time.LocalDateTime.now();
        var diferencaEmHoras = java.time.Duration.between(agora, instrucao.getDataHora()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Instrução só pode ser cancelada com antecedência mínima de 24 horas!");
        }

        instrucao.cancelar(dados.motivo());
    }
}