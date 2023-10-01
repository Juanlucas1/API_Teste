package com.api.api_funcionarios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.api_funcionarios.DTOS.FuncionariosDTO;
import com.api.api_funcionarios.Funcionarios.FuncionariosService;

@RestController
@RequestMapping("/api")
public class FuncionariosController {

    @Autowired
    private FuncionariosService funcionariosService;

    @GetMapping("/funcionarios")
    public ResponseEntity<List<FuncionariosDTO>> listarFuncionariosDTO() {
        List<FuncionariosDTO> funcionariosDTO = funcionariosService.listarFuncionariosDTO();
        return ResponseEntity.ok(funcionariosDTO);
    }

    @PostMapping("/funcionarios")
    public ResponseEntity<FuncionariosDTO> criarFuncionario(@RequestBody String jsonInput) throws Exception {
        FuncionariosDTO novoFuncionarioDTO = funcionariosService.criarFuncionarioComJSON(jsonInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionarioDTO);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionariosDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        FuncionariosDTO funcionarioDTO = funcionariosService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionarioDTO);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionariosDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionariosDTO funcionarioDTO) {
        FuncionariosDTO funcionarioAtualizadoDTO = funcionariosService.atualizarFuncionario(id, funcionarioDTO);
        return ResponseEntity.ok(funcionarioAtualizadoDTO);
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        funcionariosService.excluirFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}

