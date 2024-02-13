package br.com.totvs.transporte.domain.usecase;

import br.com.totvs.transporte.domain.adapters.VeiculoUseCaseImpl;
import br.com.totvs.transporte.domain.entity.Veiculo;
import br.com.totvs.transporte.domain.exception.VeiculoException;
import br.com.totvs.transporte.domain.port.repository.VeiculoRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoUseCaseImplTests {

    @InjectMocks
    private VeiculoUseCaseImpl veiculoUseCase;

    @Mock
    private VeiculoRepository veiculoRepository;

    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

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
        Veiculo veiculo = new Veiculo();
        veiculo.setCriadoEm(new Date());
        veiculo.setNome("Bravo");
        veiculo.setMarca("Fiat");
        veiculo.setAnoFabricacao(2013);
        veiculo.setModelo("Sporting");

        when(veiculoRepository.findVeiculo(veiculo)).thenReturn(Optional.of(veiculo));

        assertThatThrownBy(() -> veiculoUseCase.cadastrarVeiculo(veiculo))
                .isInstanceOf(VeiculoException.class);
        verify(veiculoRepository, never()).cadastrarVeiculo(veiculo);
    }

    @Test
    void calcularPrevisaoGastosVeiculosTest(){

    }

}
