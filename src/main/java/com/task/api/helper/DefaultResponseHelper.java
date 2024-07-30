package com.task.api.helper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultResponseHelper<T> extends DefaultResponseMessage {
    private T data;

    public DefaultResponseHelper()
    {
        super(true);
    }

    public DefaultResponseHelper(Boolean success)
    {
        super(success);
    }

    public DefaultResponseHelper(Boolean success, String message) {
        super(success, message);
    }

    public DefaultResponseHelper(T data) {
        this.data = data;
    }

    public DefaultResponseHelper(String message, T data) {
        super(message);
        this.data = data;
    }

    public DefaultResponseHelper(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
