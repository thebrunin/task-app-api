package com.task.api.config.exceptions;

public class UserEmailAlreadyExistsException extends RuntimeException{
    public UserEmailAlreadyExistsException() {
        super("E-mail já cadastrado na base de dados");
    }
}
