package dev.jays.ecommerce.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiEntity<T> extends ApiResponseObject {
    private T data;
    public ApiEntity(T data){
        super();
        this.data= data;
    }
    public ApiEntity(String message, T data){
        super();
        setMessage(message);
        this.data = data;
    }
    public ApiEntity(String message){
        super();
        setMessage(message);
    }
}

