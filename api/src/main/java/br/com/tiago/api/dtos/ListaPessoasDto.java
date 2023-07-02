package br.com.tiago.api.dtos;

import lombok.Data;

@Data
public class ListaPessoasDto {

	private Long id;
	private String nome;
	private String nomeDepartamento;
	private Long sum_duracao;
	
	public ListaPessoasDto(Long id, String nome, String nomeDepartamento, Long sum_duracao) {
		this.id = id;
		this.nome = nome;
		this.nomeDepartamento = nomeDepartamento;
		this.sum_duracao = sum_duracao;
	}
}
