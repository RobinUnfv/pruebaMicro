package com.robin.msvc_compras.controller;


import com.robin.msvc_compras.model.dto.CompraDto;
import com.robin.msvc_compras.model.entity.CompraEntity;
import com.robin.msvc_compras.services.CompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/compra")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @GetMapping("/compras")
    public ResponseEntity<List<CompraEntity>> getAllComprasParametros(
            @RequestParam("flag") boolean flag,
            @RequestHeader("appCallerName") String appCallerName) throws Exception {

        log.info("[CompraController] - getAllComprasParametros >> appCallerName = "+appCallerName);
        if (flag) {
            throw new Exception("Error forzado para testing");
        }
        return ResponseEntity.ok(compraService.getAllCompras());
    }

    @GetMapping
    public ResponseEntity<List<CompraEntity>> getAllCompras() {
        log.info("[CompraController] - getAllCompras");
        return ResponseEntity.ok(compraService.getAllCompras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDto> getCompraById(@PathVariable String id) {
        log.info("[CompraController] - getCompraById: {}", id);
        return ResponseEntity.ok(compraService.getCompraById(id));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CompraEntity>> getComprasByCliente(@PathVariable String idCliente) {
        log.info("[CompraController] - getComprasByCliente: {}", idCliente);
        return ResponseEntity.ok(compraService.getComprasByCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<CompraEntity> createCompra(@RequestBody CompraEntity request) {
        log.info("[CompraController] - createCompra");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(compraService.createCompra(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompra(@PathVariable String id) {
        log.info("[CompraController] - deleteCompra: {}", id);
        compraService.deleteCompra(id);
        return ResponseEntity.noContent().build();
    }
}
