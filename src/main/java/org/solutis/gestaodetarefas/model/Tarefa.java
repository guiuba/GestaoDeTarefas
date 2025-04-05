package org.solutis.gestaodetarefas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Tarefas")
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @JsonIgnore
    @Column(name = "tarefa_id") //   id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(name = "título")
    @NotEmpty(message = "O campo título é obrigatório")
    private String titulo;

    @Column(name = "descrição")
    private String descricao;

    @Column(name = "status_tarefa")
    @NotEmpty(message = "O campo status é obrigatório. Escolha entre: " +
            "Pendente, Em andamento ou Concluída")
    private String status;


}
