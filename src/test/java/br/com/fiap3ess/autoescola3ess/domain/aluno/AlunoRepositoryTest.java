package br.com.fiap3ess.autoescola3ess.domain.aluno;

import br.com.fiap3ess.autoescola3ess.domain.Aluno;
import br.com.fiap3ess.autoescola3ess.domain.AlunoRepository;
import br.com.fiap3ess.autoescola3ess.domain.endereco.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository repository;

    @Test
    @DisplayName("Deve cadastrar um aluno corretamente")
    void cadastrarAluno() {

        Endereco endereco = new Endereco(
                "Rua das Flores",
                "123",
                "Apto 10",
                "Centro",
                "São Paulo",
                "SP",
                "01001000"
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

        Aluno alunoSalvo = repository.save(aluno);

        assertThat(alunoSalvo.getId()).isNotNull();
        assertThat(alunoSalvo.getNome()).isEqualTo("Pedro");
        assertThat(alunoSalvo.getEmail()).isEqualTo("pedro@email.com");
        assertThat(alunoSalvo.getTelefone()).isEqualTo("11999999999");
        assertThat(alunoSalvo.getCpf()).isEqualTo("12345678900");
        assertThat(alunoSalvo.isAtivo()).isTrue();

        assertThat(alunoSalvo.getEndereco()).isNotNull();
        assertThat(alunoSalvo.getEndereco().getLogradouro())
                .isEqualTo("Rua das Flores");
        assertThat(alunoSalvo.getEndereco().getCidade())
                .isEqualTo("São Paulo");
        assertThat(alunoSalvo.getEndereco().getUf())
                .isEqualTo("SP");
        assertThat(alunoSalvo.getEndereco().getCep())
                .isEqualTo("01001000");
    }
}