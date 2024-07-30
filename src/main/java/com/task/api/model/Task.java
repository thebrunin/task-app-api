package com.task.api.model;

import com.task.api.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "task")
public class Task {

    @Id
    private String id;
    private String userId;
    private String projectId;
    private TaskStatus status;

    private String name;
    private String responsibleUser;
    private String description;
    private LocalDateTime deadLine;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
