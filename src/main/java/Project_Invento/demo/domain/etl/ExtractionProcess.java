package Project_Invento.demo.domain.etl;

import Project_Invento.demo.infrastructore.config.exception.DocumentExtractException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class ExtractionProcess {

    public void extract(MultipartFile file) {

        try {
            log.info("Open file: {}", file.getOriginalFilename());

            InputStream inputStream = file.getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
              for (XWPFParagraph paragraph : paragraphs) {
                System.out.println(paragraph.getText());
            }
        } catch (IOException e) {
            log.error(
                    "Failed to read .docx file: {}",
                    file.getOriginalFilename(),e
            );
            throw new DocumentExtractException(
                    "Failed to extract document",e
            );
        }
    }
}