package net.partala.taskservice.dto.response;

import net.partala.taskservice.task.TaskPriority;
import net.partala.taskservice.task.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(

        Long id,
        String title,
        Long creatorId,
        Long assignedUserId,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime deadlineDate,
        LocalDateTime completedAt,
        TaskPriority priority
) {
}
