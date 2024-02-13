package br.com.totvs.transporte.infrastructure.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "veiculo")
@Data
public class VeiculoEntity {

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;
	@CreatedDate
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
