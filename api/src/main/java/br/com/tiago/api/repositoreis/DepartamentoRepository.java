package br.com.tiago.api.repositoreis;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.tiago.api.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query("SELECT d FROM Departamento d WHERE d.nomeDepartamento = :nomeDepartamento")
    Optional<Departamento> buscandoPorTitulo(@Param("nomeDepartamento") String nomeDepartamento);

}
