package com.robin.msvc_cliente.repository;

import com.robin.msvc_cliente.model.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, UUID> {

    // Buscar solo clientes activos
    List<ClienteEntity> findAllByActiveTrue();

    // Buscar clientes por nombre (case insensitive)
    List<ClienteEntity> findByNombreContainingIgnoreCase(String nombre);

    // Verificar si existe un cliente activo por id
    @Query("SELECT COUNT(c) > 0 FROM ClienteEntity c WHERE c.id = :id AND c.active = true")
    boolean existsByIdAndActiveTrue(UUID id);
}
