package br.com.totvs.transporte.domain.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Veiculo {

	private UUID id;
	private String nome;
	private String marca;
	private String modelo;
	private Integer anoFabricacao;
	private Double consumoMedioCidade;
	private Double consumoMedioRodovia;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Double getConsumoMedioCidade() {
		return consumoMedioCidade;
	}
	public void setConsumoMedioCidade(Double consumoMedioCidade) {
		this.consumoMedioCidade = consumoMedioCidade;
	}
	public Double getConsumoMedioRodovia() {
		return consumoMedioRodovia;
	}
	public void setConsumoMedioRodovia(Double consumoMedioRodovia) {
		this.consumoMedioRodovia = consumoMedioRodovia;
	}
	
	
}
