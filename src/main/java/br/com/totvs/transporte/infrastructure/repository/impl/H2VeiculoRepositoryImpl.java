package br.com.totvs.transporte.infrastructure.repository.impl;

import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.infrastructure.entity.VeiculoEntity;
import br.com.totvs.transporte.infrastructure.repository.SpringDataVeiculoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Veiculo> findAll() {
		return Arrays.asList( mapper.map(repository.findAll(), Veiculo[].class));
	}

	@Override
	public Optional<Veiculo> findVeiculo(Veiculo veiculo) {
		Optional<VeiculoEntity> veiculoEntity = repository.findByNomeAndMarcaAndModeloAndAnoFabricacao(veiculo.getNome(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getAnoFabricacao());
        return veiculoEntity.map(entity -> mapper.map(entity, Veiculo.class));
    }

}
