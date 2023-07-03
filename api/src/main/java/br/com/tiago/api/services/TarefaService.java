package br.com.tiago.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
		if(!verificandoDepartamento.isPresent()) {
			throw new Error("Departamento não existe");
		}
		
		Tarefa tarefa = new Tarefa(tarefaDto.getTitulo(), tarefaDto.getDescricao(), tarefaDto.getPrazo(), tarefaDto.getDuracao(), tarefaDto.getFinalizado());
		tarefa.setDepartamento(verificandoDepartamento.get());
		
		return tarefaRepository.save(tarefa);
	}
	
	@Transactional
	public Tarefa AlocaUmaPessoaNaTarefa(Long id, Long pessoa_id) {
		Optional<Tarefa> buscandoTarefa = tarefaRepository.findById(id);
		if(!buscandoTarefa.isPresent()) {
			throw new Error("Tarefa não encontrada");
		}
		
		Optional<Pessoa> buscandoPessoa = pessoaRepository.findById(pessoa_id);
		if(!buscandoPessoa.isPresent()) {
			throw new Error("Pessoa não encontrada");
		}
		
		if(!buscandoPessoa.get().getDepartamento().equals(buscandoPessoa.get().getDepartamento())) {
			throw new Error("Está no mesmo departamento");
		}
		
		Tarefa tarefa = buscandoTarefa.get();
		tarefa.setPessoa(buscandoPessoa.get());
		
		return tarefaRepository.save(tarefa);
	}
	
	@Transactional
	public Tarefa finalizarTarefa(Long id) {
		Optional<Tarefa> buscandoTarefa = tarefaRepository.findById(id);
		
		if(!buscandoTarefa.isPresent()) {
			throw new Error("Tarefa não encontrada");
		}
		
		Tarefa tarefa = buscandoTarefa.get();
		tarefa.setFinalizado(true);
		
		return tarefaRepository.save(tarefa);
	}
	
	public List<Tarefa> buscarTarefaPrazoAntigo() {
		Pageable pageable = PageRequest.of(0, 3, Sort.by("prazo").ascending());
		return tarefaRepository.buscarPorTresTarefaPrazoAntigos(pageable);
	}
}
