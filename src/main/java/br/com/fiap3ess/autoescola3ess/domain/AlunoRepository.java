package br.com.fiap3ess.autoescola3ess.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByIdAndAtivoFalse(Long id);
}