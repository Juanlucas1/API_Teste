package com.api.api_funcionarios.Funcionarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.api.api_funcionarios.Cargos.Cargos;
import com.api.api_funcionarios.Enderecos.Enderecos;
import com.api.api_funcionarios.Niveis.Niveis;
import com.api.api_funcionarios.Telefones.Telefones;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_funcionario;
    private String nome;
    private String data_nascimento;
    @Lob
    private byte[] foto;
    private float salario;
    private String data_admissao;
    private String data_demissao;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_endereco")
    @JsonManagedReference
    private Enderecos id_endereco;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_nivel")
    @JsonManagedReference
    private Niveis id_nivel;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "id_cargo")
    @JsonManagedReference
    private Cargos id_cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefones> telefones;

    public void setId_endereco(Enderecos id_endereco) {
        this.id_endereco = id_endereco;
    }
    
    public void setId_nivel(Niveis id_nivel) {
        this.id_nivel = id_nivel;
    }
    
    public void setId_cargo(Cargos id_cargo) {
        this.id_cargo = id_cargo;
    }

    public Enderecos getIdEndereco() {
        return id_endereco;
    }
    
}
