package com.task.api.model;

import com.task.api.dto.group.GroupRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "group")
public class Group {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private List<String> users;
}

