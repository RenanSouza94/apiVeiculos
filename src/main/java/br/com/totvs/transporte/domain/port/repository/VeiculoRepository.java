package br.com.totvs.transporte.domain.port.repository;

import br.com.totvs.transporte.domain.entity.Veiculo;

public interface VeiculoRepository {

	Veiculo cadastrarVeiculo(Veiculo veiculo);
}
