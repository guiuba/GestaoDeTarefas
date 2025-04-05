package org.solutis.gestaodetarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.solutis.gestaodetarefas.model.Tarefa;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class TarefaDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String status;


    public TarefaDto(Tarefa novaTarefa) {
        this.id = novaTarefa.getId();
        this.titulo = novaTarefa.getTitulo();
        this.descricao = novaTarefa.getDescricao();
        this.status = novaTarefa.getStatus();
    }


}
