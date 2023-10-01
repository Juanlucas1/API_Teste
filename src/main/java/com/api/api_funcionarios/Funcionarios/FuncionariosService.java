package com.api.api_funcionarios.Funcionarios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_funcionarios.Cargos.Cargos;
import com.api.api_funcionarios.DTOS.CargosDTO;
import com.api.api_funcionarios.DTOS.EnderecosDTO;
import com.api.api_funcionarios.DTOS.FuncionariosDTO;
import com.api.api_funcionarios.DTOS.NiveisDTO;
import com.api.api_funcionarios.Enderecos.Enderecos;
import com.api.api_funcionarios.Enderecos.EnderecosRepository;
import com.api.api_funcionarios.Niveis.Niveis;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionariosService {
    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private EnderecosRepository enderecosRepository;

    public List<FuncionariosDTO> listarFuncionariosDTO() {
        List<Funcionarios> funcionarios = funcionariosRepository.findAll();
        return funcionarios.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private FuncionariosDTO mapToDTO(Funcionarios funcionario) {
        FuncionariosDTO dto = new FuncionariosDTO();
        dto.setNome(funcionario.getNome());
        dto.setData_nascimento(funcionario.getData_nascimento());
        dto.setFoto(funcionario.getFoto());
        dto.setSalario(funcionario.getSalario());
        dto.setData_admissao(funcionario.getData_admissao());
        dto.setData_demissao(funcionario.getData_demissao());
        dto.setEndereco(mapEnderecoToDTO(funcionario.getId_endereco()));
        dto.setNivel(mapNivelToDTO(funcionario.getId_nivel()));
        dto.setCargo(mapCargoToDTO(funcionario.getId_cargo()));
        return dto;
    }

    private EnderecosDTO mapEnderecoToDTO(Enderecos endereco) {
        EnderecosDTO dto = new EnderecosDTO();
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setUf(endereco.getUf());
        return dto;
    }

    private NiveisDTO mapNivelToDTO(Niveis nivel) {
        NiveisDTO dto = new NiveisDTO();
        dto.setId_nivel(nivel.getId_nivel());
        dto.setNivel(nivel.getNivel());
        dto.setBonus(nivel.getBonus());
        return dto;
    }

    private CargosDTO mapCargoToDTO(Cargos cargo) {
        CargosDTO dto = new CargosDTO();
        dto.setId_cargo(cargo.getId_cargo());
        dto.setTitulo(cargo.getTitulo());
        return dto;
    }

    public Enderecos mapEnderecoToEntity(EnderecosDTO enderecoDTO) {
        Enderecos endereco = new Enderecos();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getUf());
        return endereco;
    }
    
    public Niveis mapNivelToEntity(NiveisDTO nivelDTO) {
        Niveis nivel = new Niveis();
        nivel.setId_nivel(nivelDTO.getId_nivel());
        nivel.setNivel(nivelDTO.getNivel());
        nivel.setBonus(nivelDTO.getBonus());
        return nivel;
    }
    
    public Cargos mapCargoToEntity(CargosDTO cargoDTO) {
        Cargos cargo = new Cargos();
        cargo.setId_cargo(cargoDTO.getId_cargo());
        cargo.setTitulo(cargoDTO.getTitulo());
        // Se houver outros campos em CargosDTO, mapeie-os aqui
        return cargo;
    }

    public FuncionariosDTO criarFuncionarioComJSON(String jsonInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        FuncionarioInputDTO inputDTO = objectMapper.readValue(jsonInput, FuncionarioInputDTO.class);
    
        // Verificar se o id_endereco fornecido já existe na tabela enderecos
        Long id_endereco = inputDTO.getEndereco().getId_endereco();
        Enderecos endereco;
        if (id_endereco != null) {
            Optional<Enderecos> enderecoExistente = enderecosRepository.findById(id_endereco);
            endereco = enderecoExistente.orElseGet(() -> mapEnderecoToEntity(inputDTO.getEndereco()));
        } else {
            // Se id_endereco não foi fornecido, crie um novo endereço
            endereco = mapEnderecoToEntity(inputDTO.getEndereco());
            endereco = enderecosRepository.save(endereco); // Salve o novo endereço no banco
        }
    
        // Mapear dados do JSON para entidades existentes
        Niveis nivel = mapNivelToEntity(inputDTO.getNivel());
        Cargos cargo = mapCargoToEntity(inputDTO.getCargo());
    
        // Criar um novo registro de Funcionarios associado às entidades mapeadas
        Funcionarios funcionario = new Funcionarios();
        funcionario.setNome(inputDTO.getNome());
        funcionario.setData_nascimento(inputDTO.getData_nascimento());
        funcionario.setSalario(inputDTO.getSalario());
        funcionario.setData_admissao(inputDTO.getData_admissao());
        
        // Definir o Enderecos associado ao funcionário
        funcionario.setId_endereco(endereco);
    
        // Definir o Niveis e Cargos associados ao funcionário
        funcionario.setId_nivel(nivel);
        funcionario.setId_cargo(cargo);
    
        // Salvar o novo Funcionarios no banco de dados
        Funcionarios novoFuncionario = funcionariosRepository.save(funcionario);
    
        // Mapear o novo funcionário para DTO e retornar
        return mapToDTO(novoFuncionario);
    }
    
    public FuncionariosDTO buscarFuncionarioPorId(Long id) {
        Funcionarios funcionario = funcionariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        return mapToDTO(funcionario);
    }
    
    private Niveis mapNivelDTOToEntity(NiveisDTO nivelDTO) {
        Niveis nivel = new Niveis();
        nivel.setId_nivel(nivelDTO.getId_nivel());
        // Mapeie outros campos aqui
        return nivel;
    }
    
    private Cargos mapCargoDTOToEntity(CargosDTO cargoDTO) {
        Cargos cargo = new Cargos();
        cargo.setId_cargo(cargoDTO.getId_cargo());
        // Mapeie outros campos aqui
        return cargo;
    }
    
    public FuncionariosDTO atualizarFuncionario(Long id, FuncionariosDTO funcionarioDTO) {
        Funcionarios funcionarioExistente = funcionariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
    
        // Atualize os campos do funcionárioExistente com base em funcionarioDTO
        funcionarioExistente.setNome(funcionarioDTO.getNome());
        funcionarioExistente.setData_nascimento(funcionarioDTO.getData_nascimento());
        funcionarioExistente.setFoto(funcionarioDTO.getFoto());
        funcionarioExistente.setSalario(funcionarioDTO.getSalario());
        funcionarioExistente.setData_admissao(funcionarioDTO.getData_admissao());
        funcionarioExistente.setData_demissao(funcionarioDTO.getData_demissao());
    
        // Mapeie os campos relacionados a Endereços, Níveis e Cargos
        funcionarioExistente.setId_endereco(mapEnderecoDTOToEntity(funcionarioDTO.getEndereco()));
        funcionarioExistente.setId_nivel(mapNivelDTOToEntity(funcionarioDTO.getNivel()));
        funcionarioExistente.setId_cargo(mapCargoDTOToEntity(funcionarioDTO.getCargo()));
    
        // Continue atualizando outros campos conforme necessário
    
        Funcionarios funcionarioAtualizado = funcionariosRepository.save(funcionarioExistente);
        return mapToDTO(funcionarioAtualizado);
    }
    
    private Enderecos mapEnderecoDTOToEntity(EnderecosDTO endereco) {
        Enderecos enderecoEntidade = new Enderecos();
        enderecoEntidade.setLogradouro(endereco.getLogradouro());
        enderecoEntidade.setNumero(endereco.getNumero());
        enderecoEntidade.setComplemento(endereco.getComplemento());
        enderecoEntidade.setBairro(endereco.getBairro());
        enderecoEntidade.setCidade(endereco.getCidade());
        enderecoEntidade.setUf(endereco.getUf());
        return enderecoEntidade;
    }
    
    public void excluirFuncionario(Long id) {
        Funcionarios funcionario = funcionariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
    
        funcionariosRepository.delete(funcionario);
    }
}
