package demoApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import demoApp.Exception.ErroResponse;
import demoApp.Exception.ProdutoException;
import demoApp.Exception.PedidoException;
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

    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<ErroResponse> handleItensException(ProdutoException ex) {
        ErroResponse erro = new ErroResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(PedidoException.class)
    public ResponseEntity<ErroResponse> handlePedidoException(PedidoException ex) {
        ErroResponse erro = new ErroResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }



}

