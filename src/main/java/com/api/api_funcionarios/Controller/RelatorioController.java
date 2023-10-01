package com.api.api_funcionarios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_funcionarios.Relatorio.RelatorioService;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService; // Injete o serviço de relatório
    
    // a solicitação deve ser feita seguindo essa Lógica:/api/relatorios/funcionarios?uf=SP,MG etc

    @GetMapping("/funcionarios")
    public ResponseEntity<byte[]> gerarRelatorioPorEstado(@RequestParam("uf") String uf) {
        // Lógica para gerar o relatório em PDF
        byte[] pdfContent = relatorioService.gerarRelatorioPorEstado(uf);
        
        // Retorne o PDF no ResponseEntity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_funcionarios.pdf");
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }
}
