package br.com.totvs.transporte.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "veiculo")
@Data
public class VeiculoEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;
	private Date criadoEm;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String marca;
	@Column(nullable = false)
	private String modelo;
	@Column(nullable = false)
	private Integer anoFabricacao;
	@Column(nullable = false)
	private Double consumoMedioCidade;
	@Column(nullable = false)
	private Double consumoMedioRodovia;
}
