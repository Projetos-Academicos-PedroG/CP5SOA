package br.com.fiap3ess.autoescola3ess.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull Role perfil
) {
}