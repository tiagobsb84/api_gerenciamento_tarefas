package br.com.tiago.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
		pessoa = pessoaService.adicionarPessoa(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa atualizarPessoa = pessoaService.alterarPessoa(id, pessoa);
		return ResponseEntity.ok().body(atualizarPessoa);
	}
}
