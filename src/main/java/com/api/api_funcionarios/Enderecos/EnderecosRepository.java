package com.api.api_funcionarios.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecosRepository extends JpaRepository <Enderecos, Long>{
    
    @Query("SELECT MAX(e.id_endereco) FROM Enderecos e")
     Long findMaxIdEndereco();
    
}
