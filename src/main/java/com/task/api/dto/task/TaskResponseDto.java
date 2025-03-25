package com.task.api.dto.task;

import com.task.api.enums.TaskStatus;
import com.task.api.model.Task;

import java.time.LocalDateTime;

public record TaskResponseDto(
        String name,
        String responsibleUser,
        String userId,
        String description,
        TaskStatus status,
        LocalDateTime deadLine
) {
        public TaskResponseDto(Task entity) {
                this(entity.getName(),
                        entity.getResponsibleUser(),
                        entity.getUserId(),
                        entity.getDescription(),
                        entity.getStatus(),
                        entity.getDeadLine());
        }
}
