package br.com.totvs.transporte.domain.adapters;

import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.exception.PrevisaoGastosException;
import br.com.totvs.transporte.domain.exception.VeiculoException;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;
import br.com.totvs.transporte.util.DataUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class VeiculoUseCaseImpl implements VeiculoUseCase{

	private VeiculoRepository repository;
	
	public VeiculoUseCaseImpl(VeiculoRepository repository) {
		this.repository = repository;
	}

	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public void cadastrarVeiculo(Veiculo veiculo) {
		validaDadosVeiculo(veiculo);
		Optional<Veiculo> veiculoOptional = repository.findVeiculo(veiculo);
		if(veiculoOptional.isPresent()){
			throw new VeiculoException("Esse veículo já foi cadastrado no dia "+ DataUtil.dateToString( veiculoOptional.get().getCriadoEm()));
		}
		veiculo.setCriadoEm(new Date());
		repository.cadastrarVeiculo(veiculo);
	}

	@Override
	public List<PrevisaoGastos> calcularPrevisaoGastosVeiculos(Double valorCombustivel, Double totalKmCidade, Double totalKmRodovia) {
		validaDadosPrevisao(valorCombustivel, totalKmCidade, totalKmRodovia);

		List<Veiculo> listaVeiculos = repository.findAll();
		if(listaVeiculos.isEmpty()){
			throw new VeiculoException("Não há veículos cadastrados");
		}
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
		return new PrevisaoGastos(veiculo, df.format( qtdTotalCombustivel), df.format(valorTotalCombustivel));

	}

	private Double calcularQuantidadeCombustivel(Double km, Double consumoMedio){
		if(consumoMedio == 0.0){
			return 0.0;
		}
		return km / consumoMedio;
	}

	private Double calcularValorTotalCombustivel(Double quantidadeCombustivel, Double valorLitroCombustivel){
		return quantidadeCombustivel * valorLitroCombustivel;
	}

	private void validaDadosPrevisao(Double valorCombustivel, Double totalKmCidade, Double totalKmRodovia){
		if(valorCombustivel == null || valorCombustivel <= 0){
			throw new PrevisaoGastosException("Valor do combustível inválido");
		}
		if(totalKmCidade != null && totalKmCidade < 0){
			throw new PrevisaoGastosException("Quilometragem da cidade inválida");
		}

		if(totalKmRodovia != null && totalKmRodovia < 0){
			throw new PrevisaoGastosException("Quilometragem da rodovia inválida");
		}
	}

	private void validaDadosVeiculo(Veiculo veiculo){
		try {
			Objects.requireNonNull(veiculo, "Dados do veículo inválidos");
			Objects.requireNonNull(veiculo.getMarca(), "Marca deve ser informada");
			Objects.requireNonNull(veiculo.getNome(), "Nome deve ser informado");
			Objects.requireNonNull(veiculo.getModelo(), "Modelo deve ser informado");
			Objects.requireNonNull(veiculo.getAnoFabricacao(), "Ano fabricação deve ser informado");
		} catch (NullPointerException e){
			throw new VeiculoException(e.getMessage());
		}

	}

}
