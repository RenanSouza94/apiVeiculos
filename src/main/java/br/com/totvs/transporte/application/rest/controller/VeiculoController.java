package br.com.totvs.transporte.application.rest.controller;

import br.com.totvs.transporte.application.rest.input.PrevisaoGastosRequest;
import br.com.totvs.transporte.application.rest.output.PrevisaoGastosResponse;

import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import br.com.totvs.transporte.domain.exception.PrevisaoGastosException;
import br.com.totvs.transporte.domain.exception.VeiculoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.totvs.transporte.application.rest.input.NovoVeiculoRequest;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.port.usecase.VeiculoUseCase;

import java.util.Arrays;
import java.util.List;
@Tag(name = "Api de cadastro de veículos e cálculo de gastos")
@RestController
@Validated
@RequestMapping(path = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController  {

	@Autowired
	private VeiculoUseCase veiculoUseCase;
	
	@Autowired
	private ModelMapper mapper;
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
							@ApiResponse(responseCode = "400", description = "Falha na validação"),
							@ApiResponse(responseCode = "500", description = "Erro interno")})
	@Operation(summary = "Cadastra um novo veículo", method = "POST")
	@PostMapping
	public ResponseEntity<String> cadastrarVeiculo(@RequestBody @Valid NovoVeiculoRequest novoVeiculoRequest){
		try {
			veiculoUseCase.cadastrarVeiculo(mapper.map(novoVeiculoRequest, Veiculo.class));
			return ResponseEntity.status(HttpStatus.CREATED).body("Veículo cadastrado com sucesso");
		} catch(VeiculoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cálculo efetuado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Falha na validação"),
			@ApiResponse(responseCode = "500", description = "Erro interno")})
	@Operation(summary = "Caáculo o gasto de combustível dos veículos com base na distancia e valor da gasolina")
	@GetMapping(value = "/calcularGastos")
	public ResponseEntity<?> calcularGastos(@RequestBody @Valid PrevisaoGastosRequest request){
		try {
			List<PrevisaoGastos> previsaoGastosList = veiculoUseCase.calcularPrevisaoGastosVeiculos(request.getValorGasolina(), request.getTotalKmCidade(), request.getTotalKmRodovia());
			return ResponseEntity.status(HttpStatus.OK).body( Arrays.asList( mapper.map(previsaoGastosList, PrevisaoGastosResponse[].class)));
		} catch(PrevisaoGastosException | VeiculoException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
