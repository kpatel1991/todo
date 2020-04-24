package com.kunjan.todo.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.abbreviate;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlers {

//    @Autowired
//    private Clock clock;

    @ExceptionHandler(value = { HttpMediaTypeNotAcceptableException.class })
    public final ResponseEntity<ErrorResponse> handleHttpMessageConversionException(
            HttpMediaTypeNotAcceptableException t) {
        log.error("Request processing failed!" + t.getMessage(), t);
        String errorMessage = StringUtils.contains(t.getMessage(), ":")
                ? t.getMessage().split(":")[0] : abbreviate(t.getMessage(), 120);

        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.toErrorCode(t),
                errorMessage,
                ZonedDateTime.now(),
                null
        );
        return response(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> response(ErrorResponse errorResponse, HttpStatus httpStatus) {
        return response(errorResponse, httpStatus, new HashMap<>());
    }

    private ResponseEntity<ErrorResponse> response(ErrorResponse errorResponse, HttpStatus httpStatus,
                                                   Map<String, String> additionalHandler){
        HttpHeaders headers = new HttpHeaders();
        additionalHandler.forEach(headers::add);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(errorResponse, headers, httpStatus);
    }
}
