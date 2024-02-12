package br.com.totvs.transporte.application.rest.controllers;

import br.com.totvs.transporte.application.rest.input.PrevisaoGastosRequest;
import br.com.totvs.transporte.application.rest.output.PrevisaoGastosResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.totvs.transporte.application.rest.input.NovoVeiculoRequest;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;

@RestController
@RequestMapping(path = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

	@Autowired
	private VeiculoUseCase veiculoUseCase;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> cadastrarVeiculo(@RequestBody NovoVeiculoRequest novoVeiculoRequest){
		try {
			veiculoUseCase.cadastrarVeiculo(mapper.map(novoVeiculoRequest, Veiculo.class));
			return ResponseEntity.status(HttpStatus.CREATED).body("Veículo cadastrado com sucesso");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
