package com.robin.msvc_cliente.controller;

import com.robin.msvc_cliente.model.dto.ClienteDto;
import com.robin.msvc_cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        log.info("[ClienteController] - getAllClientes");
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDto>> getAllClienteWithParameter(
            @RequestParam("flag") boolean flag,
            @RequestHeader("appCallerName") String appCallerName
    ) throws Exception {
        log.info("[ClienteController] - getAllClienteWithParameter");
        System.out.println("[ClienteController] - getAllClienteWithParameter");
        System.out.println("flag: " + flag);
        System.out.println("appCallerName: " + appCallerName);
        if (flag) {
            throw new Exception("Flag is true.");
        }
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable UUID id) {
        log.info("[ClienteController] - getClienteById: {}", id);
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto request) {
        log.info("[ClienteController] - createCliente");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.createCliente(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(
            @PathVariable UUID id,
            @RequestBody ClienteDto request) {
        log.info("[ClienteController] - updateCliente: {}", id);
        return ResponseEntity.ok(clienteService.updateCliente(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
        log.info("[ClienteController] - deleteCliente (lógico): {}", id);
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}