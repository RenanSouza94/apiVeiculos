package br.com.totvs.transporte.infrastructure.repository.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.infrastructure.entity.VeiculoEntity;
import br.com.totvs.transporte.infrastructure.repository.SpringDataVeiculoRepository;
import org.springframework.stereotype.Component;

@Component
public class H2VeiculoRepositoryImpl implements VeiculoRepository{

	@Autowired
	private SpringDataVeiculoRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Veiculo cadastrarVeiculo(Veiculo veiculo) {
		VeiculoEntity entity = repository.save(mapper.map(veiculo, VeiculoEntity.class));
		veiculo.setId(entity.getId());
		return veiculo;
	}

}
