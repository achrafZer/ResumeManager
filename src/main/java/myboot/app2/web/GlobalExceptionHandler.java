package myboot.app2.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("date du problème : ", LocalDateTime.now().toLocalDate());
        body.put("message", "Une erreur est survenue");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
