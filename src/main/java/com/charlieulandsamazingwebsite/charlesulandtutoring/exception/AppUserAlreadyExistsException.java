package com.charlieulandsamazingwebsite.charlesulandtutoring.exception;

public class AppUserAlreadyExistsException extends RuntimeException {
    public AppUserAlreadyExistsException() {
        super("User already exists");
    }

}
