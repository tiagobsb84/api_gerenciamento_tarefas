package br.com.tiago.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.dtos.TarefaDto;
import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.models.Tarefa;
import br.com.tiago.api.repositoreis.DepartamentoRepository;
import br.com.tiago.api.repositoreis.PessoaRepository;
import br.com.tiago.api.repositoreis.TarefaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Tarefa AdicionarTarefa(TarefaDto tarefaDto) {
		Optional<Departamento> verificandoDepartamento = departamentoRepository.findById(tarefaDto.getDepartamento_id());
		Optional<Pessoa> buscandoPessoa = pessoaRepository.findById(tarefaDto.getPessoa_id());
	
		if(!verificandoDepartamento.isPresent()) {
			throw new Error("Departamento não existe");
		}
		
		Tarefa tarefa = new Tarefa(tarefaDto.getTitulo(), tarefaDto.getDescricao(), tarefaDto.getPrazo(), tarefaDto.getDuracao(), tarefaDto.getFinalizado());
		tarefa.setDepartamento(verificandoDepartamento.get());
		tarefa.setPessoaAlocada(buscandoPessoa.get());
		
		return tarefaRepository.save(tarefa);
	}
}
