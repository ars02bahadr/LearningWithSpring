package com.mycompany.learning.Domain.Results;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResult<T> {
    private List<T> items;
    private int pageNumber = 1;
    private int pageSize = 5;
    private int totalCount = 0;
    private boolean isSuccessful = true;
    private List<String> errorMessages;

    @JsonIgnore
    private int statusCode = 200;

    public PagedResult(List<T> items, int totalCount, int pageNumber, int pageSize) {
        this.items = items;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PagedResult(int statusCode, List<String> errorMessages) {
        this.isSuccessful = false;
        this.statusCode = statusCode;
        this.errorMessages = errorMessages;
    }

    public PagedResult(int statusCode, String errorMessage) {
        this.isSuccessful = false;
        this.statusCode = statusCode;
        this.errorMessages = List.of(errorMessage);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    public static <T> PagedResult<T> succeed(List<T> items, int totalCount, int pageNumber, int pageSize) {
        return new PagedResult<>(items, totalCount, pageNumber, pageSize);
    }

    public static <T> PagedResult<T> failure(int statusCode, List<String> errorMessages) {
        return new PagedResult<>(statusCode, errorMessages);
    }

    public static <T> PagedResult<T> failure(int statusCode, String errorMessage) {
        return new PagedResult<>(statusCode, errorMessage);
    }

    public static <T> PagedResult<T> failure(String errorMessage) {
        return new PagedResult<>(500, errorMessage);
    }

    public static <T> PagedResult<T> failure(List<String> errorMessages) {
        return new PagedResult<>(500, errorMessages);
    }

    public List<T> getItems() { return items; }
    public void setItems(List<T> items) { this.items = items; }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public boolean isSuccessful() { return isSuccessful; }
    public void setSuccessful(boolean successful) { isSuccessful = successful; }

    public List<String> getErrorMessages() { return errorMessages; }
    public void setErrorMessages(List<String> errorMessages) { this.errorMessages = errorMessages; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
}

