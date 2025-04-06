package org.solutis.gestaodetarefas.customExceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

//@Component
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Tarefa já existente!")
//@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TarefaJaExistenteException extends RuntimeException{
    public TarefaJaExistenteException(String message) {
        super(message);
    }

    public TarefaJaExistenteException() {

        super("Tarefa já existente!");
    }
}
