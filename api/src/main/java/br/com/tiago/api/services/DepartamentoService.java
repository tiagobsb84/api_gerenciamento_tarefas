package br.com.tiago.api.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.repositoreis.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Transactional
	public Departamento adicionandoDeparatamento(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}
}
