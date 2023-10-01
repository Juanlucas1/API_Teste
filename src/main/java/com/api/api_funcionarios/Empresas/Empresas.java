package com.api.api_funcionarios.Empresas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empresa;
    private String razao_social;
    private String cnpj;
    private String telefone;
    private String email; 
    
}
