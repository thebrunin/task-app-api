package com.task.api.helper;

import lombok.Data;

@Data
public class DefaultResponseStatus {

    boolean status;

    public DefaultResponseStatus() {}

    public DefaultResponseStatus(boolean success) {
        this.status = success;
    }
}
