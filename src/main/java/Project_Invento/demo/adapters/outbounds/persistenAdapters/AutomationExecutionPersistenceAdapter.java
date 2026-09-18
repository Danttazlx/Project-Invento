package Project_Invento.demo.adapters.outbounds.persistenAdapters;

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

        JpaAutomationExecutionEntity entity =
                mapper.toEntity(execution);

        JpaAutomationExecutionEntity savedEntity =
                repository.save(entity);

        AutomationExecution savedExecution =
                mapper.toDomain(savedEntity);

        return savedExecution;
    }

}
