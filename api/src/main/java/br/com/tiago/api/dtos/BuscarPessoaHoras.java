package br.com.tiago.api.dtos;

import lombok.Data;

@Data
public class BuscarPessoaHoras {

	private Long id;
	private String nome;
	private Double avg_duracao;
	
	public BuscarPessoaHoras(Long id, String nome, Double avg_duracao) {
		this.id = id;
		this.nome = nome;
		this.avg_duracao = avg_duracao;
	}
}
