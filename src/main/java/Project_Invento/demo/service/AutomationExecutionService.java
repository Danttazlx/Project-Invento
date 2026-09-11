package Project_Invento.demo.service;

import Project_Invento.demo.dto.AutomationExecutionResponse;
import Project_Invento.demo.exception.InvalidFileException;
import Project_Invento.demo.exception.NotFoundException;
import Project_Invento.demo.handler.GlobalExceptionHandler;
import Project_Invento.demo.model.AutomationExecution;
import Project_Invento.demo.model.ExecutionStatus;
import Project_Invento.demo.repository.AutomationExecutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AutomationExecutionService {

    private final AutomationExecutionRepository repository;


    public AutomationExecutionResponse validateFile(MultipartFile file) {

        log.info("Starting file validation: {}", file.getOriginalFilename());

        if (file.isEmpty()) {
            log.warn("Empty file received");
            throw new InvalidFileException("File cannot be empty");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null || !fileName.toLowerCase().endsWith(".docx")) {
            log.warn("Invalid file type: {}", fileName);
            throw new InvalidFileException("Only .docx files are allowed");
        }

        log.info("File validated successfully: {}", fileName);

        AutomationExecution model = new AutomationExecution();

        model.setFileName(file.getOriginalFilename());
        model.setCreatedAt(LocalDateTime.now());
        model.setStatus(ExecutionStatus.RECEIVED);

        AutomationExecution saved = repository.save(model);

        AutomationExecutionResponse responseDto = new AutomationExecutionResponse();

        responseDto.setId(model.getId());
        responseDto.setFileName(model.getFileName());
        responseDto.setCreatedAt(model.getCreatedAt());
        responseDto.setStatus(model.getStatus());

        return responseDto;

    }
}
