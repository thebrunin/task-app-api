package com.task.api.dto.task;

import com.task.api.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TaskRequestDto(
        @NotBlank(message = "Task name cannot be blank")
        String name,
        String responsibleUser,
        String userId,
        String description,
        TaskStatus status,
        LocalDateTime deadLine
) {
        public TaskRequestDto(String name,
                              String responsibleUser,
                              String userId,
                              String description,
                              TaskStatus status,
                              LocalDateTime deadLine) {
                this.name = name;
                this.responsibleUser = responsibleUser;
                this.userId = userId;
                this.description = description;
                this.status = status;
                this.deadLine = deadLine;
        }

        public TaskRequestDto() {
                this(null, null, null, null, null, null);
        }
}
