package br.com.fiap3ess.autoescola3ess.service;

import br.com.fiap3ess.autoescola3ess.domain.endereco.DadosCep;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CepService {

    private final RestClient restClient;

    public CepService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public DadosCep consultarCep(String cep) {

        String cepFormatado = cep.replaceAll("\\D", "");

        return restClient.get()
                .uri("/{cep}/json", cepFormatado)
                .retrieve()
                .body(DadosCep.class);
    }
}