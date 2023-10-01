package com.api.api_funcionarios.Telefones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.api.api_funcionarios.Funcionarios.Funcionarios;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Telefones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_telefone;
    private String numero;
    private String tipo_fone;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    @JsonBackReference // Evita recursão infinita na serialização JSON
    private Funcionarios funcionario;
    
}
