package br.com.tiago.api.dtos;

import lombok.Data;

@Data
public class ListaDepartamentoDto {

	private Long id;
	private String titulo;
	private Long count_pessoa;
	private Long count_tarefas;
	
	public ListaDepartamentoDto(Long id, String titulo, Long count_pessoa, Long count_tarefas) {
		this.id = id;
		this.titulo = titulo;
		this.count_pessoa = count_pessoa;
		this.count_tarefas = count_tarefas;
	}
}
