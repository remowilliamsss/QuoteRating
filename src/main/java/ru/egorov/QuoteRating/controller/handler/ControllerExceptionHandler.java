package ru.egorov.QuoteRating.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.egorov.QuoteRating.dto.ErrorDto;
import ru.egorov.QuoteRating.exception.QuoteNotAddedException;
import ru.egorov.QuoteRating.exception.UserNotCreatedException;
import ru.egorov.QuoteRating.exception.VoteNotCountedException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(UserNotCreatedException e) {
        log.error("Handled the exception:", e);

        return new ResponseEntity<>(new ErrorDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(QuoteNotAddedException e) {
        log.error("Handled the exception:", e);

        return new ResponseEntity<>(new ErrorDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(VoteNotCountedException e) {
        log.error("Handled the exception:", e);

        return new ResponseEntity<>(new ErrorDto(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
