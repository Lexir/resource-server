package dev.salex.resourceserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ResourceControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String details = ex.getFieldErrors()
                .parallelStream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", ", "[", "]"));
        return Mono.just(new Error(HttpStatus.BAD_REQUEST, ex.getObjectName() + details,null));
    }



    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Error onConstraintValidationException(ConstraintViolationException e) {

        String details = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ", "[", "]"));


        return new Error(HttpStatus.BAD_REQUEST, details, null);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Error onConstraintValidationException(IllegalStateException e) {

//        String details = e.getConstraintViolations()
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.joining(", ", "[", "]"));


        return new Error(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    public static class Error{
        private int errorCode;
        private String error;
        private String errorMessage;
        private List<String> fieldErrors = new ArrayList<>();

        public Error(HttpStatus status, String message, List<String> fieldErrors ) {
            this.errorCode = status.value();
            this.error = status.name();
            this.errorMessage = message;
            this.fieldErrors = fieldErrors;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public List<String> getFieldErrors() {
            return fieldErrors;
        }

        public void setFieldErrors(List<String> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }
    }

}