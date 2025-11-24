package net.partala.taskservice.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.partala.taskservice.task.TaskPriority;

import java.time.LocalDateTime;

public record TaskRequest(

        @NotBlank
        String title,
        @Future
        LocalDateTime deadlineDateTime,
        @NotNull
        TaskPriority priority
) {
}
