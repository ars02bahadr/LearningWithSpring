package com.mycompany.learning.Domain.Results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private T data;
    private List<String> errorMessages;
    private boolean isSuccessful = true;

    @JsonIgnore
    private int statusCode = 200;

    public Result(T data) {
        this.data = data;
    }

    public Result(int statusCode, List<String> errorMessages) {
        this.isSuccessful = false;
        this.statusCode = statusCode;
        this.errorMessages = errorMessages;
    }

    public Result(int statusCode, String errorMessage) {
        this.isSuccessful = false;
        this.statusCode = statusCode;
        this.errorMessages = List.of(errorMessage);
    }

    public static <T> Result<T> succeed(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> failure(int statusCode, List<String> errorMessages) {
        return new Result<>(statusCode, errorMessages);
    }

    public static <T> Result<T> failure(int statusCode, String errorMessage) {
        return new Result<>(statusCode, errorMessage);
    }

    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(500, errorMessage);
    }

    public static <T> Result<T> failure(List<String> errorMessages) {
        return new Result<>(500, errorMessages);
    }

    // Getters and Setters
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public List<String> getErrorMessages() { return errorMessages; }
    public void setErrorMessages(List<String> errorMessages) { this.errorMessages = errorMessages; }

    public boolean isSuccessful() { return isSuccessful; }
    public void setSuccessful(boolean successful) { isSuccessful = successful; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
}
