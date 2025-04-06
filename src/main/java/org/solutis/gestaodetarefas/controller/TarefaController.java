package org.solutis.gestaodetarefas.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.solutis.gestaodetarefas.customExceptions.TarefaJaExistenteException;
import org.solutis.gestaodetarefas.customExceptions.TarefaNaoEncontradaException;
import org.solutis.gestaodetarefas.dto.ErroDTO;
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
//@ControllerAdvice
@RequestMapping("/api/tarefas")
@Tag(name = "TarefaController", description = "RESTful API para Gestão de Tarefas")
public class TarefaController {
    private TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @ExceptionHandler({
            TarefaJaExistenteException.class,
            TarefaNaoEncontradaException.class})

    @Operation(summary = "Cria uma nova tarefa", description = "Cria uma nova tarefa com os campos título, descrição e status")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaDto> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
          return tarefaService.criarTarefa(tarefa);
    }

    @Operation(summary = "Lista todas as tarefas", description = "Lista todas as tarefas armazenadas no banco de memória")
    @GetMapping
    public ResponseEntity<?> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @Operation(summary = "Busca uma tarefa pelo id", description = "Busca uma tarefa, através do id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto>  buscarTarefa(@PathVariable Long id) {
        return tarefaService.buscarTarefa(id);
    }

    @Operation(summary = "Atualiza o status de uma tarefa pelo id", description = "Atualiza o status de uma tarefa pelo id, através do id informado")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> atualizarStatus(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {

        return tarefaService.atualizarStatus(id, novaTarefa);
    }
    @Operation(summary = "Deleta uma tarefa pelo id", description = "Deleta uma tarefa pelo id, através do id informado",
            responses = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa não encontrada",
                    content = @Content(schema = @Schema(implementation = ErroDTO.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTarefa(@PathVariable Long id) {
        return tarefaService.excluirTarefa(id);
    }
}
