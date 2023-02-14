package ru.job4j.shortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> handleException(Exception e) throws IOException {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.ofEntries(
                        Map.entry("message", e.getMessage()),
                        Map.entry("type", e.getClass())).toString());
    }
}
