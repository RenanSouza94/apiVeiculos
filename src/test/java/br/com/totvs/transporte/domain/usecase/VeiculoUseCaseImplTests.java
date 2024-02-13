package br.com.totvs.transporte.domain.usecase;

import br.com.totvs.transporte.domain.adapters.VeiculoUseCaseImpl;
import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.exception.VeiculoException;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoUseCaseImplTests {

    @InjectMocks
    private VeiculoUseCaseImpl veiculoUseCase;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Test
    void saveVeiculoTest(){
        Veiculo veiculo = new Veiculo();
        veiculo.setNome("Bravo");
        veiculo.setMarca("Fiat");
        veiculo.setAnoFabricacao(2013);
        veiculo.setModelo("Sporting");

        when(veiculoRepository.findVeiculo(veiculo)).thenReturn(Optional.empty());

        veiculoUseCase.cadastrarVeiculo(veiculo);

        verify(veiculoRepository, atLeastOnce()).cadastrarVeiculo(veiculo);
    }

    @Test
    void saveVeiculoJaExistenteTest(){
        Veiculo veiculo = populaVeiculo();

        when(veiculoRepository.findVeiculo(veiculo)).thenReturn(Optional.of(veiculo));

        assertThatThrownBy(() -> veiculoUseCase.cadastrarVeiculo(veiculo))
                .isInstanceOf(VeiculoException.class);
        verify(veiculoRepository, never()).cadastrarVeiculo(veiculo);
    }

    @Test
    @DisplayName("Teste para verificar o calculo da previsao dos gastos")
    void calcularPrevisaoGastosVeiculosTest(){
        List<Veiculo> listaVeiculo = new ArrayList<>();
        listaVeiculo.add(populaVeiculo());
        when(veiculoRepository.findAll()).thenReturn(listaVeiculo);
        List<PrevisaoGastos> previsao =  veiculoUseCase.calcularPrevisaoGastosVeiculos(5.5, 60.5, 99.0);
        assertThat(previsao).hasSize(1);
        assertThat(previsao.get(0).getValorTotalCombustivelGasto()).isEqualTo("110,72");
        assertThat(previsao.get(0).getQtdLitrosCombustivelGasto()).isEqualTo("20,13");
    }
    @Test
    @DisplayName("Teste para verificar a validação e veiculos nao cadastrados")
    void calcularPrevisaoGastosVeiculosSemVeiculosTest(){
        assertThatThrownBy(() -> veiculoUseCase.calcularPrevisaoGastosVeiculos(8.5, 10.0, 55.5)).isInstanceOf(VeiculoException.class);
    }

    private Veiculo populaVeiculo(){
        Veiculo veiculo = new Veiculo();
        veiculo.setCriadoEm(new Date());
        veiculo.setNome("Bravo");
        veiculo.setMarca("Fiat");
        veiculo.setAnoFabricacao(2013);
        veiculo.setModelo("Sporting");
        veiculo.setConsumoMedioCidade(7.8);
        veiculo.setConsumoMedioRodovia(8.0);
        return veiculo;
    }

}
