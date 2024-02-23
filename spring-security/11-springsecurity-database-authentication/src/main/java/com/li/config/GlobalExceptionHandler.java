package com.li.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public String handleException() {
        log.debug("==========>GlobalExceptionHandler.handleException.");
        return "error";
    }
}
