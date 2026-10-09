package br.com.fiap3ess.autoescola3ess.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String login, Role perfil) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPerfil());
    }
}