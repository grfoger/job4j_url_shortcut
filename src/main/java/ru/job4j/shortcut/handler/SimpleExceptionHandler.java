package ru.job4j.shortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@ControllerAdvice
public class SimpleExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExceptionHandler.class.getSimpleName());
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {NullPointerException.class, IllegalArgumentException.class})
    public void handleException(Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpStatus.BAD_REQUEST.value());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(Map.ofEntries(
                Map.entry("message", e.getMessage()),
                Map.entry("type", e.getClass())
        )));
        LOGGER.error(e.getLocalizedMessage());
    }
}
