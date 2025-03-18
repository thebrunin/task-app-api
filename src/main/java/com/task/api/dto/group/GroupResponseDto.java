package com.task.api.dto.group;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record GroupResponseDto(
        String code,

        @NotBlank(message = "Name cannot be blank")
        String name,

        String description,

        List<String> users
) {
}
