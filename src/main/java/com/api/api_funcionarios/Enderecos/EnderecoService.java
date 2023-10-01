package com.api.api_funcionarios.Enderecos;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.api_funcionarios.DTOS.EnderecosDTO;

import jakarta.transaction.Transactional;

public class EnderecoService {
    @Autowired
    private EnderecosRepository enderecosRepository;

    @Transactional
    public Enderecos saveWithNextId(EnderecosDTO enderecoDTO) {
        // Encontre o maior id_endereco atual na tabela enderecos
        Long maxIdEndereco = enderecosRepository.findMaxIdEndereco();

        // Calcule o próximo id_endereco disponível
        Long nextIdEndereco = (maxIdEndereco != null) ? maxIdEndereco + 1 : 1;

        // Defina o próximo id_endereco no DTO de endereço
        enderecoDTO.setId_endereco(nextIdEndereco);

        // Mapeie o DTO de endereço para a entidade Enderecos
        Enderecos endereco = mapEnderecoToEntity(enderecoDTO);

        // Salve o novo endereço no banco de dados
        return enderecosRepository.save(endereco);
    }

    private Enderecos mapEnderecoToEntity(EnderecosDTO enderecoDTO) {
        return null;
    }

    
}
