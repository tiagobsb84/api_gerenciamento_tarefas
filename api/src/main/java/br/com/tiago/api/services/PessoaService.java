package br.com.tiago.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.repositoreis.PessoaRepository;
import br.com.tiago.api.services.exceptions.NotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Adicionar pessoa
	@Transactional
	public Pessoa adicionarPessoa(Pessoa pessoa) {
		
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
}
