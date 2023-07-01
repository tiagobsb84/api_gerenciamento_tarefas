package br.com.tiago.api.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiago.api.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
