package org.ferdev.challengetenpo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidException extends RuntimeException {

    private final BindingResult errors;

    public ValidException(BindingResult errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return getValidationMessage(this.errors);
    }

    @Override
    public String getMessage() {
        return this.getMessages().toString();
    }

    private static List<String> getValidationMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(ValidException::getValidationMessage)
                .collect(Collectors.toList());
    }

    private static String getValidationMessage(ObjectError error) {
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s, but it was %s", message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }

}
