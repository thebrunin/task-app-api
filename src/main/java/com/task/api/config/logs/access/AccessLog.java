package com.task.api.config.logs.access;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
public class AccessLog {

    @Id
    private String id;
    @Indexed
    private String userId;
    private String endpoint;
    private Date date;
    private Number secondsDuration;
    private String payload;
}
