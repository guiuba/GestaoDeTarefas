package org.solutis.gestaodetarefas.controller;


import jakarta.validation.Valid;
import org.solutis.gestaodetarefas.customExceptions.TarefaJaExistenteException;
import org.solutis.gestaodetarefas.customExceptions.TarefaNaoEncontradaException;
import org.solutis.gestaodetarefas.dto.TarefaDto;
import org.solutis.gestaodetarefas.model.Tarefa;
import org.solutis.gestaodetarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@ControllerAdvice
@RequestMapping("/api/tarefas")
public class TarefaController {
    private TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @ExceptionHandler({
            TarefaJaExistenteException.class,
            TarefaNaoEncontradaException.class})

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDto> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
          return tarefaService.criarTarefa(tarefa);
    }

    @GetMapping
    public List<TarefaDto> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto>  buscarTarefa(@PathVariable Long id) {
        return tarefaService.buscarTarefa(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> atualizarStatus(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {

        return tarefaService.atualizarStatus(id, novaTarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTarefa(@PathVariable Long id) {
        return tarefaService.excluirTarefa(id);
    }
}
