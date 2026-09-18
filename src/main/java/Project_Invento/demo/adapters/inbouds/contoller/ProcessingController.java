package Project_Invento.demo.adapters.inbouds.contoller;

import Project_Invento.demo.dto.ExecutionResponseDto;
import Project_Invento.demo.application.service.ExecutionService;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExecutionResponseDto createExecution (
                @RequestParam("file") MultipartFile file) {
            return serviceAutomation.validateFile(file);
    }


}
