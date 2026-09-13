package Project_Invento.demo.etl.extract;

import Project_Invento.demo.exception.DocumentExtractException;
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

            InputStream inputStream = file.getInputStream();           // o inputStream consegue lê o bytes do MultipartFile
            XWPFDocument document = new XWPFDocument(inputStream);     // XPTFDocumen recebe por parametro os bytes, conseguindo manipular
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                System.out.println(paragraph.getText());
            }
        } catch (IOException e) {                                      // trata a Exception que pode acontecer, IOException
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