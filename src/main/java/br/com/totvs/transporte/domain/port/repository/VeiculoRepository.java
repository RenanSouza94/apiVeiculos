package br.com.totvs.transporte.domain.port.repository;

import br.com.totvs.transporte.domain.entity.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {

	Veiculo cadastrarVeiculo(Veiculo veiculo);

	List<Veiculo> findAll();

	Optional<Veiculo> findVeiculo(Veiculo veiculo);
}
