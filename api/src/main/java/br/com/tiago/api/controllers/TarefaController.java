package br.com.tiago.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}