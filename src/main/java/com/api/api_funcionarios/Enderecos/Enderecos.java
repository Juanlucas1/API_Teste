package com.api.api_funcionarios.Enderecos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api.api_funcionarios.Funcionarios.Funcionarios;
import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Enderecos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @OneToMany(mappedBy = "id_endereco", fetch = FetchType.EAGER) 
    @JsonBackReference
    private List<Funcionarios> funcionarios;

    
}
