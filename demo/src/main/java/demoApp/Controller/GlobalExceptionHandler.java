package demoApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import demoApp.Exception.ErroResponse;
import demoApp.Exception.RegisterException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<ErroResponse> handleRegisterException(RegisterException ex) {
        ErroResponse erro = new ErroResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
