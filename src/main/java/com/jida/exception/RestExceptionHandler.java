package com.jida.exception;

import com.jida.dto.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<BaseResponse> handleCustomException(CustomException e, HttpServletRequest request) {
        log.warn(String.format("[%s Error] : %s %s", e.getExceptionCode().getStatus(), request.getMethod(), request.getRequestURI()));
        return BaseResponse.toCustomErrorResponse(e.getExceptionCode());
    }
}
