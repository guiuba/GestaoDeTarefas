package org.solutis.gestaodetarefas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.solutis.gestaodetarefas.customExceptions.TarefaJaExistenteException;
import org.solutis.gestaodetarefas.customExceptions.TarefaNaoEncontradaException;
import org.solutis.gestaodetarefas.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<ErroDTO> handleTarefaNaoEncontrada(TarefaNaoEncontradaException ex, HttpServletRequest request) {
        System.out.println(">>> Entrou no handler de TarefaNaoEncontradaException");
        ErroDTO erro = new ErroDTO(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(TarefaJaExistenteException.class)
    public ResponseEntity<ErroDTO> handleTarefaJaExistente(TarefaJaExistenteException ex, HttpServletRequest request) {
        ErroDTO erro = new ErroDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}


