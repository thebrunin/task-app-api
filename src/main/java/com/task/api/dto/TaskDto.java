package com.task.api.dto;

import java.time.LocalDateTime;

public record TaskDto(String name, String responsibleUser, String description, LocalDateTime deadline) {
}
