package Project_Invento.demo.adapters.outbounds.entities;

import Project_Invento.demo.domain.model.ExecutionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "automation_executions")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class JpaAutomationExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Enumerated(EnumType.STRING)
    private ExecutionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String errorMessage;






}