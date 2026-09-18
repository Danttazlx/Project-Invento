package Project_Invento.demo.adapters.outbounds.repository;

import Project_Invento.demo.adapters.outbounds.entities.JpaAutomationExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationExecutionRepository extends JpaRepository<JpaAutomationExecutionEntity,Long> {
}
