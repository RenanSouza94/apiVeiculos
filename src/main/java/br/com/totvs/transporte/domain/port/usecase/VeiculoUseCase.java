package br.com.totvs.transporte.domain.port.usecase;

import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import br.com.totvs.transporte.domain.entity.Veiculo;

import java.util.List;

public interface VeiculoUseCase {

	void cadastrarVeiculo(Veiculo veiculo);

	List<PrevisaoGastos> calcularPrevisaoGastosVeiculos(Double valorCombustivel, Double totalKmCidade, Double totalKmRodovia);

}
