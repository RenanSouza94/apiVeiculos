package br.com.totvs.transporte.application.rest.input;

import lombok.Data;

@Data
public class NovoVeiculoRequest {

	private String nome;
	private String marca;
	private String modelo;
	private Integer anoFabricacao;
	private Double consumoMedioCidade;
	private Double consumoMedioRodovia;
	
	
}
