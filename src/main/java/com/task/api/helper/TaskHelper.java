package com.task.api.helper;

import com.task.api.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskHelper {
    private String userId;
    private String projectId;
    private TaskStatus status;

    private String name;
    private String responsibleUser;
    private String description;
    private LocalDateTime deadLine;
    private LocalDateTime createdAt;
}
