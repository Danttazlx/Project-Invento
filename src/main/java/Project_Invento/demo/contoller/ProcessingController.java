package Project_Invento.demo.contoller;

import Project_Invento.demo.dto.AutomationExecutionResponse;
import Project_Invento.demo.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/executions")
@RequiredArgsConstructor
public class ProcessingController {

    private final ExecutionService serviceAutomation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutomationExecutionResponse createExecution (
                       @RequestParam("file") MultipartFile file) {
           AutomationExecutionResponse dto = serviceAutomation.validateFile(file);
        return dto;
    }


}
