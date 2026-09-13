package Project_Invento.demo.etl;

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

    private XWPFDocument openDocument(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();

            return new XWPFDocument(inputStream);

        } catch (IOException e) {
            log.error("Failed to read .docx file: {}", file.getOriginalFilename(), e);

            throw new DocumentExtractException(
                    "Failed to extract document",
                    e
            );
        }
    }
}