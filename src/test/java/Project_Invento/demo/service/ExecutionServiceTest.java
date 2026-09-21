package Project_Invento.demo.service;


import Project_Invento.demo.application.service.ExecutionService;
import Project_Invento.demo.domain.etl.ExtractionProcess;
import Project_Invento.demo.infrastructore.config.exception.InvalidFileException;
import Project_Invento.demo.ports.out.AutomationExecutionRepositoryPort;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        MultipartFile file = mock(MultipartFile.class);         //  a gente Mocka um arquivo e salvo em file
        when(file
                .isEmpty())
                .thenReturn(true);       //   aqui é aonde a gente forca a exception return isEmpty = true

        assertThrows(
                InvalidFileException.class,                    //   aqui gente coloca a exception que eu espero
                () -> executionService.validateFile(file));    //   aqui a gente chama o metodo pasando o file Mockado
    }

    ;

    @Test
    void exceptionFomatInvalid() {

        MultipartFile file = mock(MultipartFile.class);          // mocka o file

        when(file
                .isEmpty())
                .thenReturn(false);                       //  verifica pra ser verdadeiro

        when(file
                .getOriginalFilename())
                .thenReturn("Arquivo.pdf");              // simula pra ser .pdf

        assertThrows(
                InvalidFileException.class,
                () -> executionService.validateFile(file));     // chama o metodo pra executar


    }
}


