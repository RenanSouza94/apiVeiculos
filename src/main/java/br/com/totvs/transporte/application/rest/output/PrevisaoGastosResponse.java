package br.com.totvs.transporte.application.rest.output;

import lombok.Data;

@Data
public class PrevisaoGastosResponse {

	private String nome;
	private String marca;
	private Integer ano;
	private String qtdLitrosCombustivelGasto;
	private String valorTotalCombustivelGasto;

}
