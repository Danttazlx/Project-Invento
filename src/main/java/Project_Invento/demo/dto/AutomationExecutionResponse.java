package Project_Invento.demo.dto;

import Project_Invento.demo.model.ExecutionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AutomationExecutionResponse {

    private Long id;
    private String fileName;
    private ExecutionStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;

}
