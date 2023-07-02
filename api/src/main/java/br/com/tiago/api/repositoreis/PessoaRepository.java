package br.com.tiago.api.repositoreis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tiago.api.dtos.ListaPessoasDto;
import br.com.tiago.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT new br.com.tiago.api.dtos.ListaPessoasDto(p.id, p.nome, d.nomeDepartamento, sum(t.duracao)) FROM Pessoa p JOIN p.tarefas t JOIN p.departamento d GROUP BY p.id, d.nomeDepartamento")
	public List<ListaPessoasDto> buscarTodosDepartamentoComTotalDuracaoTarefa();
}
