package br.com.tiago.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.repositoreis.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa adicionarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
}
