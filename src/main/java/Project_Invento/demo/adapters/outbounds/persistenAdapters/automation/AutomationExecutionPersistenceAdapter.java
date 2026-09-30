package Project_Invento.demo.adapters.outbounds.persistenAdapters.automation;

import Project_Invento.demo.adapters.outbounds.entities.JpaAutomationExecutionEntity;
import Project_Invento.demo.adapters.outbounds.mapper.AutomationExecutionMapper;
import Project_Invento.demo.adapters.outbounds.repository.AutomationExecutionRepository;
import Project_Invento.demo.domain.model.AutomationExecution;
import Project_Invento.demo.ports.out.AutomationExecutionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutomationExecutionPersistenceAdapter
                implements AutomationExecutionRepositoryPort {

    private final AutomationExecutionRepository repository;
    private final AutomationExecutionMapper mapper;


    @Override
    public AutomationExecution save(AutomationExecution execution) {

        JpaAutomationExecutionEntity entityJpa =
                mapper.toEntity(execution);

        JpaAutomationExecutionEntity savedEntity =
                repository.save(entityJpa);

        return mapper.toDomain(savedEntity);
    }

}
