package br.com.totvs.transporte.application.rest.controller;

import br.com.totvs.transporte.application.rest.input.PrevisaoGastosRequest;
import br.com.totvs.transporte.application.rest.output.PrevisaoGastosResponse;

import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.totvs.transporte.application.rest.input.NovoVeiculoRequest;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

	@Autowired
	private VeiculoUseCase veiculoUseCase;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<String> cadastrarVeiculo(@RequestBody NovoVeiculoRequest novoVeiculoRequest){
		try {
			veiculoUseCase.cadastrarVeiculo(mapper.map(novoVeiculoRequest, Veiculo.class));
			return ResponseEntity.status(HttpStatus.CREATED).body("Veículo cadastrado com sucesso");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping(value = "/calcularGastos")
	public ResponseEntity<List<PrevisaoGastosResponse>> calcularGastos(@RequestBody PrevisaoGastosRequest request){
		try {
			List<PrevisaoGastos> previsaoGastosList = veiculoUseCase.calcularPrevisaoGastosVeiculos(request.getValorGasolina(), request.getTotalKmCidade(), request.getTotalKmRodovia());
			return ResponseEntity.status(HttpStatus.OK).body( Arrays.asList( mapper.map(previsaoGastosList, PrevisaoGastosResponse[].class)));
		} catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
