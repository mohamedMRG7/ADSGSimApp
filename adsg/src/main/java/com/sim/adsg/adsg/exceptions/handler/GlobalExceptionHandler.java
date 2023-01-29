package com.sim.adsg.adsg.exceptions.handler;


import com.sim.adsg.adsg.exceptions.BaseException;
import com.sim.adsg.adsg.exceptions.pojo.ExceptionResponse;
import com.sim.adsg.adsg.pojo.common.response.ApiBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiBaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        final String[] msg = {""};
        Map<String, String> errors = new HashMap<>();
        String requestUUID = (String) request.getAttribute("requestUUID");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            msg[0] = msg[0] + "Field : " + fieldName + " has error " + errorMessage + ", ";
        });
        return new ResponseEntity<>
                (new ApiBaseResponse<>(Boolean.FALSE,HttpStatus.SERVICE_UNAVAILABLE,
                        new ExceptionResponse(requestUUID,msg[0])),HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiBaseResponse> handleBusinessExceptions(BaseException e , HttpServletRequest request) {
        String requestUUID = (String) request.getAttribute("requestUUID");
        log.error("Error in Request {}", requestUUID);
        e.printStackTrace();
        return new ResponseEntity<>
                (new ApiBaseResponse<>(Boolean.FALSE,e.getHttpStatus(),
                        new ExceptionResponse(requestUUID,e.getMessage())),e.getHttpStatus());
    }
//
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiBaseResponse> handleOtherExceptions(Exception e , HttpServletRequest request) {
        String requestUUID = (String) request.getAttribute("requestUUID");
        log.error("Error in Request {}", requestUUID);
        e.printStackTrace();
        return new ResponseEntity<>
                (new ApiBaseResponse<>(Boolean.FALSE,HttpStatus.SERVICE_UNAVAILABLE,
                        new ExceptionResponse(requestUUID,e.getMessage())),HttpStatus.SERVICE_UNAVAILABLE);
    }

    private String getCallingController(Exception e) {
        String controllerName = "";
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            if (element.getClassName().contains("controller")) {
                controllerName = element.getClassName();
                try {
                    Class<?> clazz  = Class.forName(controllerName);
                    controllerName = clazz.getSimpleName();
                } catch (ClassNotFoundException ex) {
                    controllerName = element.getClassName();
                }
                break;
            }
        }
        return controllerName;
    }
}
