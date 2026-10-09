package br.com.fiap3ess.autoescola3ess.domain.agenda;

import br.com.fiap3ess.autoescola3ess.domain.Aluno;
import br.com.fiap3ess.autoescola3ess.domain.endereco.Endereco;
import br.com.fiap3ess.autoescola3ess.domain.instrutor.Especialidade;
import br.com.fiap3ess.autoescola3ess.domain.instrutor.Instrutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class InstrucaoRepositoryTest {

    @Autowired
    private InstrucaoRepository repository;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    @DisplayName("Deve cadastrar uma instrução corretamente")
    void cadastrarInstrucao() {

        Endereco endereco = new Endereco(
                "Rua das Flores",
                "100",
                "Casa",
                "Centro",
                "São Paulo",
                "SP",
                "01001-000"
        );

        Aluno aluno = new Aluno(
                null,
                "Pedro",
                "pedro@email.com",
                "11999999999",
                "12345678900",
                endereco,
                true
        );

        entityManager.persist(aluno);

        Instrutor instrutor = new Instrutor(
                null,
                "João da Silva",
                "joao@email.com",
                "11988888888",
                "123456789",
                Especialidade.CARROS,
                endereco,
                true
        );

        entityManager.persist(instrutor);

        LocalDateTime dataHora = LocalDateTime.now()
                .plusDays(1)
                .withNano(0);

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dataHora,
                null
        );

        Instrucao instrucaoSalva = repository.save(instrucao);

        assertThat(instrucaoSalva.getId()).isNotNull();
        assertThat(instrucaoSalva.getAluno()).isEqualTo(aluno);
        assertThat(instrucaoSalva.getInstrutor()).isEqualTo(instrutor);
        assertThat(instrucaoSalva.getDataHora()).isEqualTo(dataHora);
        assertThat(instrucaoSalva.getMotivoCancelamento()).isNull();
    }

    @Test
    @DisplayName("Deve verificar se instrutor está ocupado no horário")
    void verificarInstrutorOcupado() {

        Endereco endereco = new Endereco(
                "Rua das Flores",
                "100",
                "Casa",
                "Centro",
                "São Paulo",
                "SP",
                "01001-000"
        );

        Aluno aluno = new Aluno(
                null,
                "Pedro",
                "pedro@email.com",
                "11999999999",
                "12345678900",
                endereco,
                true
        );

        entityManager.persist(aluno);

        Instrutor instrutor = new Instrutor(
                null,
                "João da Silva",
                "joao@email.com",
                "11988888888",
                "123456789",
                Especialidade.CARROS,
                endereco,
                true
        );

        entityManager.persist(instrutor);

        LocalDateTime dataHora = LocalDateTime.now()
                .plusDays(1)
                .withNano(0);

        repository.saveAndFlush(new Instrucao(
                null,
                aluno,
                instrutor,
                dataHora,
                null
        ));

        boolean resultado = repository.existsByInstrutorIdAndDataHora(
                instrutor.getId(),
                dataHora
        );

        assertThat(resultado).isTrue();
    }
}