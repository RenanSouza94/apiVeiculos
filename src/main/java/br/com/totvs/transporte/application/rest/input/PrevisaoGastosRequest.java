package br.com.totvs.transporte.application.rest.input;

import lombok.Getter;

@Getter
public class PrevisaoGastosRequest {

	private Double valorGasolina;
	private Double totalKmCidade;
	private Double totalKmRodovia;
}
