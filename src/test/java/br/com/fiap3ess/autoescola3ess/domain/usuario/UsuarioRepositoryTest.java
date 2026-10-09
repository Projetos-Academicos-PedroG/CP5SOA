package br.com.fiap3ess.autoescola3ess.domain.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    @DisplayName("Deve cadastrar um usuário corretamente")
    void cadastrarUsuario() {

        Usuario usuario = new Usuario(
                "pedro",
                "123456",
                Role.ADMIN
        );

        Usuario usuarioSalvo = repository.save(usuario);

        assertThat(usuarioSalvo.getId()).isNotNull();
        assertThat(usuarioSalvo.getLogin()).isEqualTo("pedro");
        assertThat(usuarioSalvo.getSenha()).isEqualTo("123456");
        assertThat(usuarioSalvo.getPerfil()).isEqualTo(Role.ADMIN);
    }
}