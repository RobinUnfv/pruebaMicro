package com.robin.msvc_cliente.service;

import com.robin.msvc_cliente.model.dto.ClienteDto;

import java.util.List;
import java.util.UUID;

public interface IClienteService {

    List<ClienteDto> getAllClientes();
    ClienteDto getClienteById(UUID id);
    ClienteDto createCliente(ClienteDto request);
    ClienteDto updateCliente(UUID id, ClienteDto request);
    void deleteCliente(UUID id);
}
