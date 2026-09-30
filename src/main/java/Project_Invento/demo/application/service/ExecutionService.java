package Project_Invento.demo.application.service;

import Project_Invento.demo.domain.model.AutomationExecution;
import Project_Invento.demo.domain.model.ExecutionStatus;
import Project_Invento.demo.dto.DocumentInput;
import Project_Invento.demo.dto.ExecutionResponseDto;
import Project_Invento.demo.infrastructore.exception.exceptions.InvalidFileException;
import Project_Invento.demo.ports.out.AutomationExecutionRepositoryPort;
import Project_Invento.demo.ports.out.etl.EtlProcessorPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExecutionService {

    private final EtlProcessorPort processorPort;
    private final AutomationExecutionRepositoryPort repositoryPort;

    public ExecutionResponseDto validateFile(DocumentInput file) {

        log.info("Starting file validation: {}", file.fileName());

        if (file.isEmpty()) {
            log.warn("Empty file received");
            throw new InvalidFileException("File cannot be empty");
        }

        String fileName = file.fileName();

        if (fileName == null || !fileName.toLowerCase().endsWith(".docx")) {
            log.warn("Invalid file type: {}", fileName);
            throw new InvalidFileException("Only .docx files are allowed");
        }

        log.info("File validated successfully: {}", fileName);

        AutomationExecution model = new AutomationExecution();

        model.setFileName(file.fileName());
        model.setCreatedAt(LocalDateTime.now());
        model.setStatus(ExecutionStatus.RECEIVED);

        AutomationExecution automationExecution = repositoryPort.save(model);

        processorPort.processEtl(file);

        ExecutionResponseDto responseDto = new ExecutionResponseDto();

        responseDto.setId(automationExecution.getId());
        responseDto.setFileName(automationExecution.getFileName());
        responseDto.setCreatedAt(automationExecution.getCreatedAt());
        responseDto.setStatus(automationExecution.getStatus());

        return responseDto;
    }
}