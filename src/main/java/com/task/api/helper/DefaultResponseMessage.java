package com.task.api.helper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class DefaultResponseMessage extends DefaultResponseStatus {
    private String message;

    public DefaultResponseMessage(String message) {
        this.message = message;
    }

    public DefaultResponseMessage(boolean success) {
        this.status = success;
    }

    public DefaultResponseMessage(boolean success, String message) {
        this.status = success;
        this.message = message;
    }
}
