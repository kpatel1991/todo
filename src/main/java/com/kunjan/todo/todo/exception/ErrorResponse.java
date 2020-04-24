package com.kunjan.todo.todo.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;

import static org.apache.commons.lang3.StringUtils.abbreviate;

/**
 * General class for all errors.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
    private final ZonedDateTime timestamp;
    private Object errorDetails;

    public static ErrorResponse of(ZonedDateTime timestamp, Throwable t) {
        return new ErrorResponse(
                ErrorCode.toErrorCode(t),
                abbreviate(t.getMessage(), 120),
                timestamp,
                null
        );
    }

    public static ErrorResponse of(ZonedDateTime timestamp, Throwable t, Object errorDetails) {
        return new ErrorResponse(
                ErrorCode.toErrorCode(t),
                abbreviate(t.getMessage(), 120),
                timestamp,
                errorDetails
        );
    }
}
