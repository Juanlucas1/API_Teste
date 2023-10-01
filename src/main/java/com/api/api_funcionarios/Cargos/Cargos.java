package com.api.api_funcionarios.Cargos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api.api_funcionarios.Departamentos.Departamentos;
import com.api.api_funcionarios.Funcionarios.Funcionarios;
import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cargo;

    @Column
    private String titulo;

    @ManyToMany
    @JoinTable(
        name = "departamento_tem_cargos",
        joinColumns = @JoinColumn(name = "id_cargo"), 
        inverseJoinColumns = @JoinColumn(name = "id_departamento") 
    )
    private List<Departamentos> departamentos;

    @OneToMany(mappedBy = "id_cargo")
    @JsonBackReference
    private List<Funcionarios> funcionarios;
}







