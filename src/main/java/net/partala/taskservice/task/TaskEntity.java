package net.partala.taskservice.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue
    private Long id;


    private String title;

    private Long creatorId;

    private Long assignedUserId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deadlineDateTime;

    private LocalDateTime doneDateTime;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
}
