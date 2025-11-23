package net.partala.taskservice.task;

import net.partala.taskservice.dto.response.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse toResponse(TaskEntity entity) {

        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getCreatorId() == null ?
                    null :
                    entity.getCreatorId(),
                entity.getAssignedUserId() == null ?
                        null :
                        entity.getAssignedUserId(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getDeadlineDateTime(),
                entity.getDoneDateTime(),
                entity.getPriority()
        );
    }
}
