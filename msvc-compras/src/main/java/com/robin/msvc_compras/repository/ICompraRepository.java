package com.robin.msvc_compras.repository;

import com.robin.msvc_compras.model.entity.CompraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompraRepository extends MongoRepository<CompraEntity, String> {

    List<CompraEntity> findByIdCliente(String idCliente);

    List<CompraEntity> findByCompraContainingIgnoreCase(String texto);

}