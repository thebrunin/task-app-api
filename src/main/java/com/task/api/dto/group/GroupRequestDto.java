package com.task.api.dto.group;

import com.task.api.enums.GroupRequestOrigin;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GroupRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description,
        List<String> users,
        GroupRequestOrigin groupRequestOrigin
) {
}
