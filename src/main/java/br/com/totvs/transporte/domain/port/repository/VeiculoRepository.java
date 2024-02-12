package br.com.totvs.transporte.domain.port.repository;

import br.com.totvs.transporte.domain.entity.Veiculo;

import java.util.List;

public interface VeiculoRepository {

	Veiculo cadastrarVeiculo(Veiculo veiculo);

	List<Veiculo> findAll();
}
