package com.api.api_funcionarios.Funcionarios;

import com.api.api_funcionarios.DTOS.CargosDTO;
import com.api.api_funcionarios.DTOS.EnderecosDTO;
import com.api.api_funcionarios.DTOS.NiveisDTO;

import lombok.Data;

@Data
public class FuncionarioInputDTO {
    private String nome;
    private String data_nascimento;
    private String foto;
    private float salario;
    private String data_admissao;
    private String data_demissao;
    private EnderecosDTO endereco;
    private NiveisDTO nivel;
    private CargosDTO cargo;
    
    // Getters e setters, se necessário
}

