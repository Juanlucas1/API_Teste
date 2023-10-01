package com.api.api_funcionarios.Funcionarios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionariosRepository extends JpaRepository <Funcionarios, Long> {

    @Query("SELECT f FROM Funcionarios f WHERE f.id_endereco.uf = :uf")
    List<Funcionarios> findByEndereco_Uf(@Param("uf") String uf);
    
}
