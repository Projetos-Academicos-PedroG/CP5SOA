package br.com.fiap3ess.autoescola3ess.domain.endereco;

public record DadosCep(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String ibge,
        String gia,
        String ddd,
        String siafi,
        Boolean erro
) {
}