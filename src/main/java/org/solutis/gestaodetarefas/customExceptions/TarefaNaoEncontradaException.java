package org.solutis.gestaodetarefas.customExceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)@Component
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Tarefa não encontrada!")
//@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public TarefaNaoEncontradaException() {
        this("Tarefa não encontrada!");
    }
}
