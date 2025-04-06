package org.solutis.gestaodetarefas.service;

import org.solutis.gestaodetarefas.customExceptions.TarefaJaExistenteException;
import org.solutis.gestaodetarefas.customExceptions.TarefaNaoEncontradaException;
import org.solutis.gestaodetarefas.dao.TarefaRepo;
import org.solutis.gestaodetarefas.dto.TarefaDto;
import org.solutis.gestaodetarefas.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    TarefaRepo tarefaRepo;

    @Autowired
    public TarefaService(TarefaRepo tarefaRepo) {
        this.tarefaRepo = tarefaRepo;
    }

    public ResponseEntity<TarefaDto> criarTarefa(Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepo.findByTituloEqualsIgnoreCase(tarefa.getTitulo());
        if (tarefaExistente.isPresent()) {
            throw new TarefaJaExistenteException("Tarefa já existente!");
        }
        Tarefa novaTarefa = tarefaRepo.save(tarefa);
        TarefaDto tarefaDto = new TarefaDto(novaTarefa);
        return ResponseEntity.ok(tarefaDto);
    }



    public ResponseEntity<?> listarTarefas() {
        List<TarefaDto> listaTarefas = tarefaRepo.findAll().stream().map(TarefaDto::new).toList();
        if (listaTarefas.isEmpty()) {
            return ResponseEntity.ok("Nenhuma tarefa encontrada.");
        }
        return ResponseEntity.ok(listaTarefas);
    }

    public ResponseEntity<TarefaDto> buscarTarefa(Long id) {
        Optional<Tarefa> tarefa = tarefaRepo.findById(id);
        if (tarefa.isPresent()) {
            TarefaDto tarefaDto = new TarefaDto(tarefa.get());
            return ResponseEntity.ok(tarefaDto);
        } else {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada!");
        }
    }

    public ResponseEntity<TarefaDto> atualizarStatus(Long id, Tarefa tarefaParaAtualizar) {
        Optional<Tarefa> tarefa = tarefaRepo.findById(id);
        if (tarefa.isPresent()) {
            Tarefa tarefaAtualizada = tarefa.get();
            tarefaAtualizada.setStatus(tarefaParaAtualizar.getStatus());
            tarefaRepo.save(tarefaAtualizada);
            TarefaDto tarefaDto = new TarefaDto(tarefaAtualizada);
            return ResponseEntity.ok(tarefaDto);
        } else {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada!");
        }
    }

    public ResponseEntity<String> excluirTarefa(Long id) {
        Optional<Tarefa> tarefa = tarefaRepo.findById(id);
        if (tarefa.isPresent()) {
            String titulo = tarefa.get().getTitulo();
            tarefaRepo.delete(tarefa.get());
            return ResponseEntity.ok(String.format("Tarefa \"%s\" excluída com sucesso!", titulo));
        } else {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada!");
        }
    }
}
