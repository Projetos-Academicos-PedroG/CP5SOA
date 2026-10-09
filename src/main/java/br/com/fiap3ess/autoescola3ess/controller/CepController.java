package br.com.fiap3ess.autoescola3ess.controller;

import br.com.fiap3ess.autoescola3ess.domain.endereco.DadosCep;
import br.com.fiap3ess.autoescola3ess.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<DadosCep> consultarCep(@PathVariable String cep) {
        return ResponseEntity.ok(cepService.consultarCep(cep));
    }
}