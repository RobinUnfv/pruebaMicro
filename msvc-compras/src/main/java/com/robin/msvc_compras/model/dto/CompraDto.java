package com.robin.msvc_compras.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompraDto {
    private String id;
    private LocalDateTime fecha;
    private String compra;
    private ClienteDto cliente;
}
