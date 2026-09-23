package Project_Invento.demo.service;


import Project_Invento.demo.application.service.ExecutionService;
import Project_Invento.demo.domain.etl.ExtractionProcess;
import Project_Invento.demo.domain.model.AutomationExecution;
import Project_Invento.demo.domain.model.ExecutionStatus;
import Project_Invento.demo.dto.ExecutionResponseDto;
import Project_Invento.demo.infrastructore.config.exception.InvalidFileException;
import Project_Invento.demo.ports.out.AutomationExecutionRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExecutionServiceTest {

    @InjectMocks
    private ExecutionService executionService;
    @Mock
    private ExtractionProcess extractionProcess;
    @Mock
    private AutomationExecutionRepositoryPort automationRepositoryPort;


    @Test
    void invalidExceptionService() {

        MultipartFile file = mock(MultipartFile.class);
        when(file
                .isEmpty())
                .thenReturn(true);
        assertThrows(
                InvalidFileException.class,
                () -> executionService.validateFile(file));
    }

    ;

    @Test
    void exceptionFomatInvalid() {

        MultipartFile file = mock(MultipartFile.class);
        when(file
                .isEmpty())
                .thenReturn(false);
        when(file
                .getOriginalFilename())
                .thenReturn("Arquivo.pdf");
        assertThrows(
                InvalidFileException.class,
                () -> executionService.validateFile(file));
    }

    @Test
    void validDocxFile(){
        // Arrange
        MultipartFile file = mock(MultipartFile.class);

        when(file
                .isEmpty())
                .thenReturn(false);

        when(file
                .getOriginalFilename())
                .thenReturn(".docx");

        AutomationExecution savedExecution = new AutomationExecution();
        savedExecution.setId(1L);
        savedExecution.setFileName("Arquivo.docx");
        savedExecution.setStatus(ExecutionStatus.RECEIVED);
        savedExecution.setCreatedAt(LocalDateTime.now());

        when(automationRepositoryPort.save(any(AutomationExecution.class)))
                .thenReturn(savedExecution);

        // Asc
        ExecutionResponseDto responseDto = executionService.validateFile(file);

        // Assert
        assertEquals(1L, responseDto.getId());
        assertEquals("Arquivo.docx", responseDto.getFileName());
        assertEquals(ExecutionStatus.RECEIVED, responseDto.getStatus());

        //  verifica se o file chega no extract

        verify(extractionProcess).extract(file);
        verify(automationRepositoryPort).save(any(AutomationExecution.class));


    };


}


