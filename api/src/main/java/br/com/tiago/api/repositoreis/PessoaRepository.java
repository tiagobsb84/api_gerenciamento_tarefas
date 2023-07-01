package br.com.tiago.api.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiago.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
