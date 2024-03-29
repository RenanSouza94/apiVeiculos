package br.com.totvs.transporte.infrastructure.repository;

import br.com.totvs.transporte.infrastructure.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataVeiculoRepository extends JpaRepository<VeiculoEntity, UUID>{

    Optional<VeiculoEntity> findByNomeAndMarcaAndModeloAndAnoFabricacao(String nome, String marca, String modelo, Integer anoFabricacao);
}
