package com.task.api.dto.group;

import com.task.api.model.Group;
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
        public GroupResponseDto(Group entity) {
                this(entity.getCode(), entity.getName(), entity.getDescription(), null);
        }
}
