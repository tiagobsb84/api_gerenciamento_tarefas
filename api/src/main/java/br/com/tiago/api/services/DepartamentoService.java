package br.com.tiago.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiago.api.models.Departamento;
import br.com.tiago.api.repositoreis.DepartamentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Transactional
	public Departamento adicionandoDepartamento(Departamento departamento) {
		Optional<Departamento> departamentoAparece = departamentoRepository.buscandoPorTitulo(departamento.getNomeDepartamento());
		
		if(departamentoAparece.isPresent()) {
			throw new Error("Departamento não existe");
		}
		
		return departamentoRepository.save(departamento);
	}
}
