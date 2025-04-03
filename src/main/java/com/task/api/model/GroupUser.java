package com.task.api.model;

import com.task.api.enums.GroupUserStatus;
import com.task.api.enums.GroupRequestOrigin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "groupUser")
public class GroupUser {
    @Id
    private String id;
    private String groupId;
    private String userId;
    private GroupUserStatus status;
    private GroupRequestOrigin groupRequestOrigin;
}

