package com.robin.msvc_compras.services;


import com.robin.msvc_compras.model.dto.ClienteDto;
import com.robin.msvc_compras.model.dto.CompraDto;
import com.robin.msvc_compras.model.entity.CompraEntity;
import com.robin.msvc_compras.proxy.openfeing.IClienteServiveFeign;
import com.robin.msvc_compras.repository.ICompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompraService {

    private final ICompraRepository compraRepository;
    private final IClienteServiveFeign clienteServiceFeign;

    public List<CompraEntity> getAllCompras() {
        log.info("[CompraService] - getAllCompras");
        return compraRepository.findAll();
    }

    public CompraDto getCompraById(String id) {
        log.info("[CompraService] - getCompraById: {}", id);
        return compraRepository.findById(id)
                .map(compra -> {
                    return CompraDto.builder()
                            .id(compra.getId())
                            .fecha(compra.getFecha())
                            .compra(compra.getCompra())
                            .cliente(clienteServiceFeign.getClienteById(UUID.fromString(compra.getIdCliente())).getBody())
                            .build();
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Compra no encontrada con id: " + id));
    }

    public List<CompraEntity> getComprasByCliente(String idCliente) {
        log.info("[CompraService] - getComprasByCliente: {}", idCliente);
        return compraRepository.findByIdCliente(idCliente);
    }

    public CompraEntity createCompra(CompraEntity request) {
        log.info("[CompraService] - createCompra para cliente: {}", request.getIdCliente());
        CompraEntity entity = CompraEntity.builder()
                .id("cod-" + UUID.randomUUID().toString().substring(0, 8))
                .idCliente(request.getIdCliente())
                .fecha(request.getFecha() != null ? request.getFecha() : LocalDateTime.now())
                .compra(request.getCompra())
                .build();
        return compraRepository.save(entity);
    }



    // ─── DELETE ───────────────────────────────────────────────────────────────
    public void deleteCompra(String id) {
        log.info("[CompraService] - deleteCompra: {}", id);
        if (!compraRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Compra no encontrada con id: " + id);
        }
        compraRepository.deleteById(id);
    }


}