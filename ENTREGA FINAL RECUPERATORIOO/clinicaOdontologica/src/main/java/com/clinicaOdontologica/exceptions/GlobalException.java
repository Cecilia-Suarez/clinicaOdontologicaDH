package com.clinicaOdontologica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {
    private static final Logger LOGGER =  Logger.getLogger(GlobalException.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException ex){
        LOGGER.error(ex.getMessage());
        return new ResponseEntity("Error " + ex.getMessage(), (HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ResourceInternalServerErrorException.class)
    public ResponseEntity<String> resourceInternalServerError(ResourceInternalServerErrorException ex){
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>("Error " + ex.getMessage(), (HttpStatus.INTERNAL_SERVER_ERROR));
    }



}