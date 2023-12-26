package ru.instazoo.backend.exceptions.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.instazoo.backend.exceptions.CommentNotFoundException;
import ru.instazoo.backend.exceptions.PostNotFoundException;
import ru.instazoo.backend.payload.response.Info;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Info> handleException(PostNotFoundException exception) {
        Info data = new Info(exception.getMessage(), false);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Info> handleException(CommentNotFoundException exception) {
        Info data = new Info(exception.getMessage(), false);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
