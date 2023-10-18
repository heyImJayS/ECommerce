package dev.jays.ecommerce.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)     //After annotating this. We can now remove ControllerAdvice Class
public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }
}
