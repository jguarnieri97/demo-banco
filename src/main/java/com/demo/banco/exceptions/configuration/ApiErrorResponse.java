package com.demo.banco.exceptions.configuration;

import java.time.Instant;

public class ApiErrorResponse {
    private final int code;
    private final String detail;
    private final Instant timestamp;

    public ApiErrorResponse(int status, String message, Instant timestamp) {
        this.code = status;
        this.detail = message;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return this.code;
    }

    public String getDetail() {
        return this.detail;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}
