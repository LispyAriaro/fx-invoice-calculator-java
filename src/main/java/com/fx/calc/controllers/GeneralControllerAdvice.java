package com.fx.calc.controllers;

import com.fx.calc.exceptions.InvalidDataFormatException;
import com.fx.calc.exceptions.NotFoundException;
import com.fx.calc.models.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This helps us catch different types of exceptions that can happen and send back an
 * appropriate response back to the client
 *
 * @author efe ariaroo
 */
@RestControllerAdvice
public class GeneralControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GeneralControllerAdvice.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto handleEntityNotException(NotFoundException ex) {
        return new ResponseDto(Boolean.FALSE, ex.getMessage());
    }

    @ExceptionHandler(InvalidDataFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseDto handleInvalidDataFormatException(InvalidDataFormatException ex) {
        return new ResponseDto(Boolean.FALSE, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto handleSystemError(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseDto(Boolean.FALSE, "System error. Please contact the administrator.");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseDto handleMethodNotFound(HttpRequestMethodNotSupportedException ex) {
        return new ResponseDto(Boolean.FALSE, "Method not supported");
    }
}