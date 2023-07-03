package br.com.tiago.api.repositoreis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tiago.api.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query(value="SELECT t FROM Tarefa t WHERE t.pessoa IS NULL ORDER BY t.prazo ASC LIMIT 3", nativeQuery = true)
	public List<Tarefa> buscarPorTresTarefaPrazoAntigos();
}
