package com.example.demo.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(int status, String message, Long timestamp) {
        super(status, message, timestamp);
    }

    public List<FieldMessage> getErros() {
        return errors;
    }

    public void addError(String errorName, String errorMessage) {
        errors.add(new FieldMessage(errorName, errorMessage));
    }
}
