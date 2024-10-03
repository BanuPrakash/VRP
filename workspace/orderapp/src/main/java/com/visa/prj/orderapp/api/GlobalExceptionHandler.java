package com.visa.prj.orderapp.api;

import com.visa.prj.orderapp.service.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    // ResponseEntity is data + headers
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> message = new LinkedHashMap<>();
        message.put("timestamp", new Date());
        message.put("message", ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> message = new LinkedHashMap<>();
        message.put("timestamp", new Date());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(exception -> exception.getDefaultMessage())
                .collect(Collectors.toList());

        message.put("errors", errors);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);


    }
}
