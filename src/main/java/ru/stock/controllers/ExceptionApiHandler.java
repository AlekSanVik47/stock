package ru.stock.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stock.exceptions.DataHasNotChanged;
import ru.stock.exceptions.DataNotInDBException;
import ru.stock.exceptions.ErrorMessage;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(DataNotInDBException.class)
    public ResponseEntity<ErrorMessage> notInDBException(DataNotInDBException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(DataHasNotChanged.class)
    public ResponseEntity<ErrorMessage> hasNotInDBException(DataHasNotChanged exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }
}