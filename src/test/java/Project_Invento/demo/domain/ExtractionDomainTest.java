package Project_Invento.demo.domain.etl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtractionDomainTest {

    @Test
    void shouldExtractParagraphsFromDocument() throws IOException {

        // Arrange
        MultipartFile file = mock(MultipartFile.class);

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("Teste de extração");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.write(outputStream);

        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        when(file.getInputStream()).thenReturn(inputStream);
        when(file.getOriginalFilename()).thenReturn("teste.docx");

        ExtractionProcess extractionProcess = new ExtractionProcess();


        // Act

        List<XWPFParagraph> paragraphs =
                extractionProcess.extract(file);


        // Assert

        assertNotNull(paragraphs);

        assertEquals(1, paragraphs.size());

        assertEquals(
                "Teste de extração",
                paragraphs.get(0).getText()
        );
    }
}