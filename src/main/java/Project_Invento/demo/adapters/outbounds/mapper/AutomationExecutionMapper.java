package Project_Invento.demo.adapters.outbounds.mapper;

import Project_Invento.demo.adapters.outbounds.entities.JpaAutomationExecutionEntity;
import Project_Invento.demo.domain.model.AutomationExecution;
import org.springframework.stereotype.Component;

@Component
public class AutomationExecutionMapper {

    public JpaAutomationExecutionEntity toEntity(AutomationExecution domain) {

        JpaAutomationExecutionEntity entity =
                new JpaAutomationExecutionEntity();

        entity.setId(domain.getId());
        entity.setFileName(domain.getFileName());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setStartedAt(domain.getStartedAt());
        entity.setFinishedAt(domain.getFinishedAt());
        entity.setErrorMessage(domain.getErrorMessage());

        return entity;
    }


    public AutomationExecution toDomain(JpaAutomationExecutionEntity jpaAutomation){

        AutomationExecution domain = new AutomationExecution();

        domain.setId(jpaAutomation.getId());
        domain.setFileName(jpaAutomation.getFileName());
        domain.setStatus(jpaAutomation.getStatus());
        domain.setCreatedAt(jpaAutomation.getCreatedAt());
        domain.setStartedAt(jpaAutomation.getStartedAt());
        domain.setFinishedAt(jpaAutomation.getFinishedAt());
        domain.setErrorMessage(jpaAutomation.getErrorMessage());


        return domain;
    };



}