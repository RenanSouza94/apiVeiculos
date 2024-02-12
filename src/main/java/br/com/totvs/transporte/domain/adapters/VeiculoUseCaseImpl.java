package br.com.totvs.transporte.domain.adapters;

import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;

public class VeiculoUseCaseImpl implements VeiculoUseCase{

	private VeiculoRepository repository;
	
	public VeiculoUseCaseImpl(VeiculoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void cadastrarVeiculo(Veiculo veiculo) {
		repository.cadastrarVeiculo(veiculo);
	}

}
