package br.com.totvs.transporte.application.rest.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class NovoVeiculoRequest {

	@NotBlank(message = "O atributo nome é obrigatório")
	private String nome;
	@NotBlank(message = "O atributo marca é obrigatório")
	private String marca;
	@NotBlank(message = "O atributo modelo é obrigatório")
	private String modelo;
	@Min(value = 1886, message = "Ano de fabricação inválido")
	private Integer anoFabricacao;
	@Min(value = 0, message = "Consumo inválido")
	private Double consumoMedioCidade;
	@Min(value = 0, message = "Consumo inválido")
	private Double consumoMedioRodovia;
	
	
}
