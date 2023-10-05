package dev.jays.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDataDTO {
    private HttpStatus errorCode;
    private String message;

    public ExceptionDataDTO(HttpStatus errorCode, String message){
        this.errorCode= errorCode;
        this.message= message;
    }
}


