package com.sim.adsg.adsg.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException{

    private HttpStatus httpStatus;

    public BaseException (String message , HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
