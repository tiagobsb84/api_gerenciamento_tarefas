package br.com.tiago.api.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {

	@NotBlank
	private String nome;
	
	@NotBlank
	private Long departamento_id;
}
