package br.com.totvs.transporte.infrastructure.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "veiculo")
@Data
public class VeiculoEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;
	private String nome;
	private String marca;
	private String modelo;
	private Integer anoFabricacao;
	private Double consumoMedioCidade;
	private Double consumoMedioRodovia;
}
