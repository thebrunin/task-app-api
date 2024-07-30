package com.task.api.config.logs.error;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public class ErrorLog {

    @Id
    private String id;
    @Indexed
    private String userId;
    private String originClass;
    private String exceptionClass;
    private Date date;
    private String message;
}
