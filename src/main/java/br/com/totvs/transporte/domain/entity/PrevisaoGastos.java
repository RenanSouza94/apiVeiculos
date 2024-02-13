package br.com.totvs.transporte.domain.entity;

public class PrevisaoGastos {

    public PrevisaoGastos(Veiculo veiculo, String qtdLitrosCombustivelGasto, String valorTotalCombustivelGasto){
        this.veiculo = veiculo;
        this.qtdLitrosCombustivelGasto = qtdLitrosCombustivelGasto;
        this.valorTotalCombustivelGasto = valorTotalCombustivelGasto;
    }
    private Veiculo veiculo;
    private String qtdLitrosCombustivelGasto;
    private String valorTotalCombustivelGasto;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getQtdLitrosCombustivelGasto() {
        return qtdLitrosCombustivelGasto;
    }

    public String getValorTotalCombustivelGasto() {
        return valorTotalCombustivelGasto;
    }
}
