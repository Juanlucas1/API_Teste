package com.api.api_funcionarios.Relatorio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.api.api_funcionarios.Funcionarios.Funcionarios;
import com.api.api_funcionarios.Funcionarios.FuncionariosRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public byte[] gerarRelatorioPorEstado(String uf) {
        // Lógica para consultar funcionários do estado no banco de dados
        List<Funcionarios> funcionarios = funcionariosRepository.findByEndereco_Uf(uf);

        // Crie um documento PDF usando o Apache PDFBox
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Relatório de Funcionários do Estado: " + uf);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Funcionários:");
            contentStream.newLine();

            float yPosition = 680; // Posição vertical inicial
            for (Funcionarios funcionario : funcionarios) {
                contentStream.newLineAtOffset(0, -20); // Espaço vertical entre os nomes
                contentStream.showText("- " + funcionario.getNome());
            }

            contentStream.endText();
        } catch (IOException e) {
            // Trate exceções
        }

        // Salve o documento em um array de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            // Trate exceções
            return new byte[0];
        }
    }
}




