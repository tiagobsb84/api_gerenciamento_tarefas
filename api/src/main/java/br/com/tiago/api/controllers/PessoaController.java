package br.com.tiago.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.api.dtos.BuscarPessoaHoras;
import br.com.tiago.api.dtos.ListaPessoasDto;
import br.com.tiago.api.dtos.PessoaDto;
import br.com.tiago.api.models.Pessoa;
import br.com.tiago.api.services.PessoaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody PessoaDto pessoaDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.adicionarPessoa(pessoaDto));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa atualizarPessoa = pessoaService.alterarPessoa(id, pessoa);
		return ResponseEntity.ok().body(atualizarPessoa);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removePessoa(@PathVariable Long id) {
		pessoaService.removePessoa(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ListaPessoasDto>> listaPessoasDepartamentoDuracao() {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarTodos());
	}
	
	@GetMapping("/gastos")
	public ResponseEntity<List<BuscarPessoaHoras>> buscarPessoaNomeHorasGasta(@RequestParam String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPessoasNome(nome));
	}
	
}
