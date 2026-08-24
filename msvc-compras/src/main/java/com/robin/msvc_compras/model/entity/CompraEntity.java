package com.robin.msvc_compras.model.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "compras")
public class CompraEntity {

    @Id
    private String id;

    @Field("id_ciente")
    private String idCliente;

    @Field("fecha")
    private LocalDateTime fecha;

    @Field("compra")
    private String compra;
}