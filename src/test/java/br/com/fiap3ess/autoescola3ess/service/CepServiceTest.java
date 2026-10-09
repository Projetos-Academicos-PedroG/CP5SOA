package br.com.fiap3ess.autoescola3ess.service;

import br.com.fiap3ess.autoescola3ess.domain.endereco.DadosCep;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CepServiceTest {

    @Test
    @DisplayName("Deve consultar um CEP corretamente")
    void consultarCep() {

        CepService cepService = new CepService();

        DadosCep resultado = cepService.consultarCep("01001000");

        assertThat(resultado).isNotNull();
        assertThat(resultado.cep()).isEqualTo("01001-000");
        assertThat(resultado.logradouro()).isNotBlank();
        assertThat(resultado.localidade()).isEqualTo("São Paulo");
        assertThat(resultado.uf()).isEqualTo("SP");
    }
}