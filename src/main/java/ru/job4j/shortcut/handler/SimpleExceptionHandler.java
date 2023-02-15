package ru.job4j.shortcut.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@ControllerAdvice
public class SimpleExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExceptionHandler.class.getSimpleName());
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception e) throws JsonProcessingException {
        LOGGER.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(Map.ofEntries(
                        Map.entry("message", e.getMessage()),
                        Map.entry("type", e.getClass()))));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, f ->
                                String.format("%s. Actual value: %s", f.getDefaultMessage(), f.getRejectedValue())))
                );
    }
}
