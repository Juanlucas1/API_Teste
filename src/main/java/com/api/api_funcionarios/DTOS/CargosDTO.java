package com.api.api_funcionarios.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CargosDTO {
    private Long id_cargo;
    private String titulo;
    
}
