package br.com.tiago.api.repositoreis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.tiago.api.dtos.BuscarPessoaHoras;
import br.com.tiago.api.dtos.ListaPessoasDto;
import br.com.tiago.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT new br.com.tiago.api.dtos.ListaPessoasDto(p.id, p.nome, d.nomeDepartamento, sum(t.duracao)) FROM Pessoa p JOIN p.tarefas t JOIN p.departamento d GROUP BY p.id, d.nomeDepartamento")
	public List<ListaPessoasDto> buscarTodosDepartamentoComTotalDuracaoTarefa();

	@Query("SELECT new br.com.tiago.api.dtos.BuscarPessoaHoras(p.id, p.nome, avg(t.duracao)) FROM Pessoa p JOIN p.tarefas t WHERE p.nome = :nome GROUP BY p.id")
	public List<BuscarPessoaHoras> buscarPessoasMediaHoras(@Param("nome") String nome);
}
