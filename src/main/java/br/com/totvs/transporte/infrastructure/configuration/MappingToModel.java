package br.com.totvs.transporte.infrastructure.configuration;

import br.com.totvs.transporte.application.rest.output.PrevisaoGastosResponse;
import br.com.totvs.transporte.domain.entity.PrevisaoGastos;
import org.modelmapper.PropertyMap;

public class MappingToModel {

    public static final PropertyMap<PrevisaoGastos, PrevisaoGastosResponse> mapPrevisaoGastosToResponse = new PropertyMap<PrevisaoGastos, PrevisaoGastosResponse>() {
        @Override
        protected void configure() {
            map().setAno(source.getVeiculo().getAnoFabricacao());
            map().setNome(source.getVeiculo().getNome());
            map().setMarca(source.getVeiculo().getMarca());
        }
    };

}
