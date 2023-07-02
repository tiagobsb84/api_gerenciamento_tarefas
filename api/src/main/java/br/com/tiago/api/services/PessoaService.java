package br.com.tiago.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.dtos.BuscarPessoaHoras;
import br.com.tiago.api.dtos.ListaPessoasDto;
import br.com.tiago.api.dtos.PessoaDto;
import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.repositoreis.DepartamentoRepository;
import br.com.tiago.api.repositoreis.PessoaRepository;
import br.com.tiago.api.services.exceptions.NotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Transactional
	public Pessoa adicionarPessoa(PessoaDto pessoaDto) {
		Optional<Departamento> departamentoPeloId = departamentoRepository.findById(pessoaDto.getDepartamento_id()); 
		
		if(!departamentoPeloId.isPresent()) {
			throw new Error("Departamento não encontrado");
		}
		
		Departamento departamento = departamentoPeloId.get();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setDepartamento(departamento);
		
		return pessoaRepository.save(pessoa);
	}
	
	//Altera pessoa
	public Pessoa alterarPessoa(Long id, Pessoa pessoa) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        
        if(optionalPessoa.isEmpty()) {
        	throw new NotFoundException("Pessoa não encontrada");
        }
        
        // Atualizar os atributos da pessoaExistente com base nos dados da pessoa
        Pessoa pessoaExistente = optionalPessoa.get();
        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setDepartamento(pessoa.getDepartamento());
        pessoaExistente.setTarefas(pessoa.getTarefas());

        return pessoaRepository.save(pessoaExistente);        
    }
	
	//Deletar pessoa
	public void removePessoa(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	//Lista pessoa, departamento e total de horas
	public List<ListaPessoasDto> buscarTodos() {
		return pessoaRepository.buscarTodosDepartamentoComTotalDuracaoTarefa();
	}
	
	
	//Busca Pessoa pelo nome, periodo, media horas gastas
	public List<BuscarPessoaHoras> buscarPessoasNome(String nome) {
		return pessoaRepository.buscarPessoasMediaHoras(nome);
	}
}
