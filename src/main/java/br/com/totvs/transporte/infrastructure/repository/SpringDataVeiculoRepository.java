package br.com.totvs.transporte.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.totvs.transporte.infrastructure.entity.VeiculoEntity;

public interface SpringDataVeiculoRepository extends JpaRepository<VeiculoEntity, UUID>{

}
