package br.com.tiago.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDto {

	@NotBlank
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private Date prazo;
	
	@NotBlank
	private Integer duracao;
	
	@NotBlank
	private Boolean finalizado;
	
	@NotBlank
	private Long departamento_id;
	
	@NotBlank
	private Long pessoa_id;
}
