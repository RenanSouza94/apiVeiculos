package br.com.totvs.transporte.infrastrucutre.repository;

import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.infrastructure.entity.VeiculoEntity;
import br.com.totvs.transporte.infrastructure.repository.SpringDataVeiculoRepository;
import br.com.totvs.transporte.infrastructure.repository.impl.H2VeiculoRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VeiculoRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SpringDataVeiculoRepository repository;

    @Autowired
    private H2VeiculoRepositoryImpl h2VeiculoRepository;

    @Test
    @DisplayName("Teste para verificar cadastro de veículos")
    public void cadastrarVeiculoTest(){
        Veiculo veiculo = popularVeiculo();
        Veiculo veiculoCriado = h2VeiculoRepository.cadastrarVeiculo(veiculo);
        assertThat(veiculoCriado.getId()).isNotNull();

    }
    @Test
    @DisplayName("Teste para verificar a consulta de lista de veiculos")
    public void findAllTest(){
        h2VeiculoRepository.cadastrarVeiculo(popularVeiculo2());
        assertThat(h2VeiculoRepository.findAll()).hasSize(2);
    }

    @Test
    @DisplayName("Teste para encontrar Veiculo")
    public void findVeiculoTest(){
        Veiculo veiculo = popularVeiculo2();
        assertThat(h2VeiculoRepository.findVeiculo(veiculo)).isPresent();
    }

    @Test
    @DisplayName("Teste para nao encontrar Veiculo")
    public void notFindVeiculoTest(){
        Veiculo veiculo = popularVeiculo3();
        assertThat(h2VeiculoRepository.findVeiculo(veiculo)).isEmpty();
    }


    private Veiculo popularVeiculo(){
        Veiculo veiculo = new Veiculo();
        veiculo.setCriadoEm(new Date());
        veiculo.setNome("Bravo");
        veiculo.setMarca("Fiat");
        veiculo.setAnoFabricacao(2013);
        veiculo.setModelo("Sporting");
        veiculo.setConsumoMedioCidade(5.5);
        veiculo.setConsumoMedioRodovia(9.0);
        return veiculo;
    }

    private Veiculo popularVeiculo2(){
        Veiculo veiculo = new Veiculo();
        veiculo.setCriadoEm(new Date());
        veiculo.setNome("Ká");
        veiculo.setMarca("Ford");
        veiculo.setAnoFabricacao(2005);
        veiculo.setModelo("Basic");
        veiculo.setConsumoMedioCidade(15.5);
        veiculo.setConsumoMedioRodovia(19.0);
        return veiculo;
    }

    private Veiculo popularVeiculo3(){
        Veiculo veiculo = new Veiculo();
        veiculo.setCriadoEm(new Date());
        veiculo.setNome("Honda");
        veiculo.setMarca("Civic");
        veiculo.setAnoFabricacao(2021);
        veiculo.setModelo("Touring");
        veiculo.setConsumoMedioCidade(10.4);
        veiculo.setConsumoMedioRodovia(15.8);
        return veiculo;
    }


}
