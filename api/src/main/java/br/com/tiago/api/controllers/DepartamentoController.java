package br.com.tiago.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.services.DepartamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departamento")
@RequiredArgsConstructor
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;
	
	@PostMapping
	public ResponseEntity<Departamento> adicionarDepartamento(@RequestBody Departamento departamento) {
		departamento = departamentoService.adicionandoDepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
	}
}
