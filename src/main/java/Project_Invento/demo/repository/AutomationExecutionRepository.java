package Project_Invento.demo.repository;

import Project_Invento.demo.model.AutomationExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationExecutionRepository extends JpaRepository<AutomationExecution,Long> {

    // Optional<AutomationExecution> findById(Long id);


}
