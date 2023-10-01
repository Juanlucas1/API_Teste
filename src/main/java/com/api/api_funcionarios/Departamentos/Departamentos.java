package com.api.api_funcionarios.Departamentos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api.api_funcionarios.Cargos.Cargos;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departamento;
    private String titulo;

    @ManyToMany(mappedBy = "departamentos")
     private List<Cargos> cargos;
    
}
