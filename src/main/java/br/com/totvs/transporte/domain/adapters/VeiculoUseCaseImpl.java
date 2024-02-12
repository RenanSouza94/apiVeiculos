package br.com.totvs.transporte.domain.adapters;

import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;

import java.util.ArrayList;
import java.util.List;

public class VeiculoUseCaseImpl implements VeiculoUseCase{

	private VeiculoRepository repository;
	
	public VeiculoUseCaseImpl(VeiculoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void cadastrarVeiculo(Veiculo veiculo) {
		repository.cadastrarVeiculo(veiculo);
	}

	@Override
	public List<PrevisaoGastos> calcularPrevisaoGastosVeiculos(Double valorCombustivel, Double totalKmCidade, Double totalKmRodovia) {
		List<Veiculo> listaVeiculos = repository.findAll();
		List<PrevisaoGastos> previsaoGastosList = new ArrayList<>();
		listaVeiculos.forEach(v ->
				previsaoGastosList.add(
						calcularPrevisaoGastos(v, valorCombustivel, totalKmCidade, totalKmRodovia)
				)
		);
		return previsaoGastosList;

	}

	private PrevisaoGastos calcularPrevisaoGastos(Veiculo veiculo, Double valorCombustivel, Double totalKmCidade, Double totalKmRodovia){
		Double qtdCombustivelCidade = calcularQuantidadeCombustivel(totalKmCidade, veiculo.getConsumoMedioCidade());
		Double qtdCombustivelRodovia = calcularQuantidadeCombustivel(totalKmRodovia, veiculo.getConsumoMedioRodovia());

		Double qtdTotalCombustivel = qtdCombustivelCidade + qtdCombustivelRodovia;
		Double valorTotalCombustivel = calcularValorTotalCombustivel(qtdTotalCombustivel, valorCombustivel);
		return new PrevisaoGastos(veiculo, qtdTotalCombustivel, valorTotalCombustivel);

	}

	private Double calcularQuantidadeCombustivel(Double km, Double consumoMedio){
		return km / consumoMedio;
	}

	private Double calcularValorTotalCombustivel(Double quantidadeCombustivel, Double valorLitroCombustivel){
		return quantidadeCombustivel * valorLitroCombustivel;
	}

}
