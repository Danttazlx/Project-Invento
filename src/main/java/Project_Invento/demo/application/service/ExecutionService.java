package Project_Invento.demo.application.service;

import Project_Invento.demo.domain.etl.ExtractionProcess;
import Project_Invento.demo.domain.model.AutomationExecution;
import Project_Invento.demo.domain.model.ExecutionStatus;
import Project_Invento.demo.dto.ExecutionResponseDto;
import Project_Invento.demo.infrastructore.config.exception.InvalidFileException;
import Project_Invento.demo.ports.out.AutomationExecutionRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ExecutionService {

    private final ExtractionProcess extractionProcess;
    private final AutomationExecutionRepositoryPort repositoryPort;

    public ExecutionService(ExtractionProcess extractionProcess,  AutomationExecutionRepositoryPort repositoryPort) {
        this.extractionProcess = extractionProcess;
        this.repositoryPort = repositoryPort;
    }

    public ExecutionResponseDto validateFile(MultipartFile file) {

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

        AutomationExecution saved = repositoryPort.save(model);

        extractionProcess.extract(file);

        ExecutionResponseDto responseDto = new ExecutionResponseDto();

        responseDto.setId(saved.getId());
        responseDto.setFileName(saved.getFileName());
        responseDto.setCreatedAt(saved.getCreatedAt());
        responseDto.setStatus(saved.getStatus());

        return responseDto;
    }
}