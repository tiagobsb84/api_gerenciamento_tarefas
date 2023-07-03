package br.com.tiago.api.repositoreis;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.tiago.api.dtos.ListaDepartamentoDto;
import br.com.tiago.api.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query("SELECT d FROM Departamento d WHERE d.nomeDepartamento = :nomeDepartamento")
    Optional<Departamento> buscandoPorTitulo(@Param("nomeDepartamento") String nomeDepartamento);

	@Query("SELECT new br.com.tiago.api.dtos.ListaDepartamentoDto(d.id, d.nomeDepartamento, count(distinct p.id), count(distinct t.id)) FROM Departamento d JOIN d.tarefa t JOIN d.pessoa p GROUP BY d.id")
	public List<ListaDepartamentoDto> buscaNoDepartamentoPessoasTarefas();
}
