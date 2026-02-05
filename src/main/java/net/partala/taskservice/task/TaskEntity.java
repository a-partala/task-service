package net.partala.taskservice.task;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Column(name = "creator_id", nullable = false, updatable = false)
    private Long creatorId;

    @Setter
    @Column(name = "assigned_user_id")
    private Long assignedUserId;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "deadline_date_time")
    private LocalDateTime deadlineDateTime;

    @Setter
    @Column(name = "done_date_time")
    private LocalDateTime doneDateTime;
}