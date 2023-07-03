package br.com.tiago.api.repositoreis;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tiago.api.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("SELECT t FROM Tarefa t WHERE t.pessoa IS NULL ORDER BY t.prazo ASC")
	public List<Tarefa> buscarPorTresTarefaPrazoAntigos(Pageable pageable);
}
