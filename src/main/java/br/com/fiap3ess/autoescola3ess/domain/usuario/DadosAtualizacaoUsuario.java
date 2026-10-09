package br.com.fiap3ess.autoescola3ess.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull Long id,
        String login,
        Role perfil
) {
}