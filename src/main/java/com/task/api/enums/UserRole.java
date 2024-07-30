package com.task.api.enums;

public enum UserRole {
    ADMIN("admin"), BASIC("basic");

    public final String label;

    UserRole(String label)
    {
        this.label = label;
    }

}
