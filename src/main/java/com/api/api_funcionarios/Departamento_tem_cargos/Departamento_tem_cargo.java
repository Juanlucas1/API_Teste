package com.api.api_funcionarios.Departamento_tem_cargos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api.api_funcionarios.Cargos.Cargos;
import com.api.api_funcionarios.Departamentos.Departamentos;

import jakarta.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departamento_tem_cargo {
    @ManyToMany
    @JoinTable(
        name = "departamento_tem_cargos",
        joinColumns = @JoinColumn(name = "id_departamento"), 
        inverseJoinColumns = @JoinColumn(name = "id_cargo") 
    )
    private List<Departamentos> departamentos;

    @ManyToMany(mappedBy = "departamentos") 
    private List<Cargos> cargos;

    
}
