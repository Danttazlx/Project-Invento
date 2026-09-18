package Project_Invento.demo.ports.out;

import Project_Invento.demo.domain.model.AutomationExecution;
import org.springframework.stereotype.Component;

@Component
public interface AutomationExecutionRepositoryPort {

    AutomationExecution save(AutomationExecution execution);


}
