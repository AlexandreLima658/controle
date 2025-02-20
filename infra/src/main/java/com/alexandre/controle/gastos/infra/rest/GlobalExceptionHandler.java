package com.alexandre.controle.gastos.infra.rest;


import com.alexandre.controle.gastos.domain.commons.exceptions.DomainException;
import com.alexandre.controle.gastos.domain.commons.exceptions.ErrorInfo;
import com.alexandre.controle.gastos.domain.commons.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE_ERROR_500 = """
           We're sorry, an internal server error has occurred.
           """;

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorInfo> handlerNotFoundException(final NotFoundException ex) {
        log.error(ex.errorInfo().message());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.errorInfo());
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<ErrorInfo> handlerDomainException(final DomainException ex) {
        log.error(ex.errorInfo().message());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.errorInfo());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> handlerException(final Exception ex) {
        log.error(ex.getMessage());
        final var error = new ErrorInfo(MESSAGE_ERROR_500);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}
