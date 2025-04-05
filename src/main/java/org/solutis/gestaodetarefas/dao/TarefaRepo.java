package org.solutis.gestaodetarefas.dao;

import jakarta.validation.constraints.NotEmpty;
import org.solutis.gestaodetarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepo extends JpaRepository<Tarefa, Long> {

    Optional<Tarefa> findByTituloEqualsIgnoreCase( String titulo);
}
