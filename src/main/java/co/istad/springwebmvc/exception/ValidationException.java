package co.istad.springwebmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    @ExceptionHandler(ResolutionException.class)
    ResponseEntity<?> handleServiceErrors(ResponseStatusException ex)
    {
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error",ex.getReason()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleDtoException(MethodArgumentNotValidException ex) {
        List<Map<String, Object>> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("reason", fieldError.getDefaultMessage());
            errors.add(error);
        });
        Map<String,Object> getError = new HashMap<>();
        getError.put("message",errors);
        return new ResponseEntity<>(getError,HttpStatus.BAD_REQUEST);
    }

}
