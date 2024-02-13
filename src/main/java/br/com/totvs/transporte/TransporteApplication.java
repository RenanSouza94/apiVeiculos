package br.com.totvs.transporte;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cadastro de veículos e controle de gastos", version = "1", description = "Api para cadastro e controle de gastos de veículos"))
public class TransporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransporteApplication.class, args);
	}

}
