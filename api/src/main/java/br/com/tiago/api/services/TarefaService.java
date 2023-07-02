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
	
	@Transactional
	public Tarefa AlocaUmaPessoaNaTarefa(Long id, Long pessoa_id) {
		Optional<Tarefa> buscandoTarefa = tarefaRepository.findById(id);
		Optional<Pessoa> buscandoPessoa = pessoaRepository.findById(pessoa_id);
		
		if(!buscandoTarefa.isPresent()) {
			throw new Error("Tarefa não encontrada");
		}
		
		if(!buscandoPessoa.isPresent()) {
			throw new Error("Pessoa não encontrada");
		}
		
		if(!buscandoPessoa.get().getDepartamento().equals(buscandoPessoa.get().getDepartamento())) {
			throw new Error("Está no mesmo departamento");
		}
		
		Tarefa tarefa = buscandoTarefa.get();
		tarefa.setPessoaAlocada(buscandoPessoa.get());
		
		return tarefaRepository.save(tarefa);
	}
}
