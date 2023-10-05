package dev.jays.ecommerce.exceptions;

import dev.jays.ecommerce.dtos.ExceptionDataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDataDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(new ExceptionDataDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }
}
