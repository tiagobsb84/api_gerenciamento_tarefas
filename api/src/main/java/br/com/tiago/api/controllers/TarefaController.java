package br.com.tiago.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.api.dtos.AlocarPessoaDto;
import br.com.tiago.api.dtos.TarefaDto;
import br.com.tiago.api.models.Tarefa;
import br.com.tiago.api.services.TarefaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;
	
	@PostMapping
	public ResponseEntity<Tarefa> adicinarTarefa(@RequestBody TarefaDto tarefaDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.AdicionarTarefa(tarefaDto));
	}
	
	@PutMapping("/alocar/{id}")
	public ResponseEntity<Tarefa> AlocaUmaPessoaNaTarefa(@PathVariable("id") Long id, @RequestBody AlocarPessoaDto alocarPessoaDto) {
		return ResponseEntity.status(HttpStatus.OK).body(tarefaService.AlocaUmaPessoaNaTarefa(id, alocarPessoaDto.getPessoa_id()));
	}
	
	@PutMapping("/finalizar/{id}")
	public ResponseEntity<Tarefa> finalizandoTarefa(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(tarefaService.finalizarTarefa(id));
	}
}
