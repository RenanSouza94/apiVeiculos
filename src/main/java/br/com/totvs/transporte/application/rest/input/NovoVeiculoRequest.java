package br.com.totvs.transporte.application.rest.input;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class NovoVeiculoRequest {

	@NotBlank(message = "O atributo nome é obrigatório")
	private String nome;
	@NotBlank(message = "O atributo marca é obrigatório")
	private String marca;
	@NotBlank(message = "O atributo modelo é obrigatório")
	private String modelo;
	@NotBlank(message = "O atributo anoFabricacao é obrigatório")
	@Min(value = 1886, message = "Ano de fabricação inválido")
	private Integer anoFabricacao;
	@Min(value = 0, message = "Consumo inválido")
	private Double consumoMedioCidade;
	@Min(value = 0, message = "Consumo inválido")
	private Double consumoMedioRodovia;
	
	
}
