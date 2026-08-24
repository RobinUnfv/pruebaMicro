package com.robin.msvc_compras.proxy.openfeing;

import com.robin.msvc_compras.model.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "msvc-cliente")
public interface IClienteServiveFeign {

    @GetMapping("/api/cliente/{id}")
    ResponseEntity<ClienteDto> getClienteById(@PathVariable UUID id);

}
