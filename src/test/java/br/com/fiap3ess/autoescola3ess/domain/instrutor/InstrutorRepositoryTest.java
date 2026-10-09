package br.com.fiap3ess.autoescola3ess.domain.instrutor;


import br.com.fiap3ess.autoescola3ess.domain.endereco.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class InstrutorRepositoryTest {

    @Autowired
    private InstrutorRepository repository;

    @Test
    @DisplayName("Deve cadastrar um instrutor corretamente")
    void cadastrarInstrutor() {

        Endereco endereco = new Endereco(
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "São Paulo",
                "SP",
                "01001000"
        );

        Instrutor instrutor = new Instrutor(
                null,
                "Carlos",
                "carlos@email.com",
                "11988888888",
                "123456789",
                Especialidade.CARROS,
                endereco,
                true
        );

        Instrutor instrutorSalvo = repository.save(instrutor);

        assertThat(instrutorSalvo.getId()).isNotNull();
        assertThat(instrutorSalvo.getNome()).isEqualTo("Carlos");
        assertThat(instrutorSalvo.getEmail()).isEqualTo("carlos@email.com");
        assertThat(instrutorSalvo.getTelefone()).isEqualTo("11988888888");
        assertThat(instrutorSalvo.getCnh()).isEqualTo("123456789");
        assertThat(instrutorSalvo.getEspecialidade())
                .isEqualTo(Especialidade.CARROS);
        assertThat(instrutorSalvo.isAtivo()).isTrue();

        assertThat(instrutorSalvo.getEndereco()).isNotNull();
        assertThat(instrutorSalvo.getEndereco().getLogradouro())
                .isEqualTo("Rua das Flores");
        assertThat(instrutorSalvo.getEndereco().getCidade())
                .isEqualTo("São Paulo");
        assertThat(instrutorSalvo.getEndereco().getUf())
                .isEqualTo("SP");
        assertThat(instrutorSalvo.getEndereco().getCep())
                .isEqualTo("01001000");
    }
}