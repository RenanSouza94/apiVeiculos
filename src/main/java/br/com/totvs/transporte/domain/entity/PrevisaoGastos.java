package br.com.totvs.transporte.domain.entity;

public class PrevisaoGastos {

    public PrevisaoGastos(Veiculo veiculo, Double qtdLitrosCombustivelGasto, Double valorTotalCombustivelGasto){
        this.veiculo = veiculo;
        this.qtdLitrosCombustivelGasto = qtdLitrosCombustivelGasto;
        this.valorTotalCombustivelGasto = valorTotalCombustivelGasto;
    }
    private Veiculo veiculo;
    private Double qtdLitrosCombustivelGasto;
    private Double valorTotalCombustivelGasto;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Double getQtdLitrosCombustivelGasto() {
        return qtdLitrosCombustivelGasto;
    }

    public Double getValorTotalCombustivelGasto() {
        return valorTotalCombustivelGasto;
    }
}
