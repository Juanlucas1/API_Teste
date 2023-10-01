package com.api.api_funcionarios.DTOS;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionariosDTO {
    private String nome;
    private String data_nascimento;
    private byte[] foto;
    private float salario;
    private String data_admissao;
    private String data_demissao;
    private EnderecosDTO endereco;
    private NiveisDTO nivel;
    private CargosDTO cargo;
}


