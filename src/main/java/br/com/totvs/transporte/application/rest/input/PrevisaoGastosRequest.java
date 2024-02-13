package br.com.totvs.transporte.application.rest.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class PrevisaoGastosRequest {

	@Min(value = 0, message = "Valor da gasolina inválido")
	@NotNull(message = "Valor da gasolina inválido")
	private Double valorGasolina;
	@Min(value = 0, message = "Quilometragem inválida")
	@NotNull(message = "Quilometragem inválida")
	private Double totalKmCidade;
	@Min(value = 0, message = "Quilometragem inválida")
	@NotNull(message = "Quilometragem inválida")
	private Double totalKmRodovia;
}
