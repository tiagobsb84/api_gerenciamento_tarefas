package br.com.tiago.api.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tiago.api.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
