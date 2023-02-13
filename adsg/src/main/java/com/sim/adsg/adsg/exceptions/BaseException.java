package com.sim.adsg.adsg.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException{

    private HttpStatus httpStatus;

    private String message;

    public BaseException (String message , HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
