package com.api.api_funcionarios.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NiveisDTO {
    private Long id_nivel;
    private String nivel;
    private float bonus;
    
}
