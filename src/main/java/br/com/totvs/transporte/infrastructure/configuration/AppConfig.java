package br.com.totvs.transporte.infrastructure.configuration;

import br.com.totvs.transporte.domain.adapters.VeiculoUseCaseImpl;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(MappingToModel.mapPrevisaoGastosToResponse);
		return modelMapper;
	}

	@Bean
	VeiculoUseCase veiculoUseCase(VeiculoRepository veiculoRepository){
		return new VeiculoUseCaseImpl(veiculoRepository);
	}
}
