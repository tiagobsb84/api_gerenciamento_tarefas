package br.com.tiago.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.services.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	@PostMapping
	public ResponseEntity<Departamento> adicionarDepartamento(@RequestBody Departamento departamento) {
		departamento = departamentoService.adicionandoDeparatamento(departamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(departamento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
