package com.robin.msvc_cliente.service;

import com.robin.msvc_cliente.model.dto.ClienteDto;

import java.util.List;
import java.util.UUID;

import com.robin.msvc_cliente.model.entity.ClienteEntity;
import com.robin.msvc_cliente.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService  {

    private final IClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ClienteDto> getAllClientes() {
        log.info("[ClienteService] - getAllClientes");
        return clienteRepository.findAllByActiveTrue()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteDto getClienteById(UUID id) {
        log.info("[ClienteService] - getClienteById: {}", id);
        return clienteRepository.findById(id)
                .filter(ClienteEntity::getActive)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));
    }

    @Transactional
    @Override
    public ClienteDto createCliente(ClienteDto request) {
        log.info("[ClienteService] - createCliente: {}", request.getNombre());
        ClienteEntity entity = ClienteEntity.builder()
                .nombre(request.getNombre())
                .montoCompras(request.getMontoCompras())
                .active(request.getActive() != null ? request.getActive() : true)
                .build();
        return toResponse(clienteRepository.save(entity));
    }

    @Transactional
    @Override
    public ClienteDto updateCliente(UUID id, ClienteDto request) {
        log.info("[ClienteService] - updateCliente: {}", id);
        ClienteEntity entity = clienteRepository.findById(id)
                .filter(ClienteEntity::getActive)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));

        entity.setNombre(request.getNombre());
        entity.setMontoCompras(request.getMontoCompras());
        if (request.getActive() != null) {
            entity.setActive(request.getActive());
        }
        return toResponse(clienteRepository.save(entity));
    }


    @Transactional
    @Override
    public void deleteCliente(UUID id) {
        log.info("[ClienteService] - deleteCliente (lógico): {}", id);
        ClienteEntity entity = clienteRepository.findById(id)
                .filter(ClienteEntity::getActive)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));
        entity.setActive(false);
        clienteRepository.save(entity);
    }

    private ClienteDto toResponse(ClienteEntity entity) {
        return ClienteDto.builder()
                .nombre(entity.getNombre())
                .montoCompras(entity.getMontoCompras())
                .active(entity.getActive())
                .build();
    }

}
