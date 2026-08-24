package com.robin.msvc_cliente.model.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private String nombre;
    private BigDecimal montoCompras;
    private Boolean active;

}
