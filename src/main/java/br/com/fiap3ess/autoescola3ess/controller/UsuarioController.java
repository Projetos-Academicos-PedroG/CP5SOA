package br.com.fiap3ess.autoescola3ess.controller;

import br.com.fiap3ess.autoescola3ess.domain.usuario.DadosAtualizacaoUsuario;
import br.com.fiap3ess.autoescola3ess.domain.usuario.DadosCadastroUsuario;
import br.com.fiap3ess.autoescola3ess.domain.usuario.DadosDetalhamentoUsuario;
import br.com.fiap3ess.autoescola3ess.domain.usuario.Usuario;
import br.com.fiap3ess.autoescola3ess.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var encoder = new BCryptPasswordEncoder();
        var senhaHash = encoder.encode(dados.senha());

        var usuario = new Usuario(dados.login(), senhaHash, dados.perfil());
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity listar() {
        var lista = repository.findAll().stream().map(DadosDetalhamentoUsuario::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}