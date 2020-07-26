package com.melath.nubecula.storage.model.exception;

public class UserStorageException extends RuntimeException {
    public UserStorageException(String message) { super(message);}
    public UserStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
