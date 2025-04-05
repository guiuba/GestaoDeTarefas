package org.solutis.gestaodetarefas.mapper;

import org.solutis.gestaodetarefas.dto.TarefaDto;
import org.solutis.gestaodetarefas.model.Tarefa;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public TarefaDto toTarefaDto(Tarefa tarefa) {
        return MODEL_MAPPER.map(tarefa, TarefaDto.class);
    }
}
