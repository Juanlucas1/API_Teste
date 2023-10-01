package com.api.api_funcionarios.Niveis;
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

public class Niveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nivel;

    @Column
    private String nivel;

    @Column
    private float bonus;

    @OneToMany(mappedBy = "id_nivel", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Funcionarios> funcionarios;

}