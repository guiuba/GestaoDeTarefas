package org.solutis.gestaodetarefas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.solutis.gestaodetarefas.customExceptions.TarefaJaExistenteException;
import org.solutis.gestaodetarefas.customExceptions.TarefaNaoEncontradaException;
import org.solutis.gestaodetarefas.dao.TarefaRepo;
import org.solutis.gestaodetarefas.dto.TarefaDto;
import org.solutis.gestaodetarefas.model.Tarefa;
import org.solutis.gestaodetarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GestaoDeTarefasApplicationTests {

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private TarefaRepo tarefaRepo;

	private Tarefa tarefa;
	private TarefaDto novaTarefaDto;

	@BeforeEach
	void setUp() {
		// deleta todos as tarefas do banco de dados antes de cada teste
		tarefaRepo.deleteAll();

		// instancia uma tarefa de teste básica
		tarefa = new Tarefa();
		tarefa.setTitulo("Tarefa Teste");
		tarefa.setDescricao("Descrição da Tarefa Teste");
		tarefa.setStatus("PENDENTE");
	}



	@Test
	void deveCriarTarefa() {

		ResponseEntity<TarefaDto> novatarefa = tarefaService.criarTarefa(tarefa);

		// Asserts
		// Status e not null
		assertEquals(HttpStatus.OK, novatarefa.getStatusCode());

		assertNotNull(novatarefa.getBody());
		novaTarefaDto =  novatarefa.getBody();

		assertNotNull(novaTarefaDto.getId());
		// verifica se os campos estão corretos
		assertEquals("Tarefa Teste", novaTarefaDto.getTitulo());
		assertEquals("Descrição da Tarefa Teste", novaTarefaDto.getDescricao());
		assertEquals("PENDENTE", novaTarefaDto.getStatus());

		//verifica se criar uma tarefa já existente retorna uma exceção
		assertThrows(TarefaJaExistenteException.class, () -> {
			tarefaService.criarTarefa(tarefa);
		});
	}

	@Test
	void deveListarTarefas() { // testGetAllTarefas

		var tarefaZero = tarefaService.listarTarefas();
		// Asserts
		// verifica se a lista está vazia antes de criar uma tarefa
		assertEquals(0, tarefaZero.size());

		// cria/adiciona uma tarefa ao banco de dados
		tarefaService.criarTarefa(tarefa);

		// cria/adiciona mais uma tarefa ao banco de dados
		Tarefa segundaTarefa = new Tarefa();
		segundaTarefa.setTitulo("Segunda Tarefa");
		segundaTarefa.setDescricao("Descrição da Segunda Tarefa");
		segundaTarefa.setStatus("EM_ANDAMENTO");
		tarefaService.criarTarefa(segundaTarefa);

		var tarefas2 = tarefaService.listarTarefas();

		// verifica se o banco de dados contém 2 tarefas e também se os campos estão corretos
		assertEquals(2, tarefas2.size());
		assertEquals(2, tarefas2.get(1).getId());
		assertEquals("Segunda Tarefa", tarefas2.get(1).getTitulo());
		assertEquals("Descrição da Segunda Tarefa", tarefas2.get(1).getDescricao());
		assertEquals("EM_ANDAMENTO", tarefas2.get(1).getStatus());

	}



	@Test
	void deveBuscarTarefa() {
		// cria/adiciona uma tarefa ao banco de dados
		ResponseEntity<TarefaDto> novaTarefa = tarefaService.criarTarefa(tarefa);
		novaTarefaDto =  novaTarefa.getBody();
        // busca esta tarefa no banco de dados
		ResponseEntity<TarefaDto> tarefaEncontrada = tarefaService.buscarTarefa(novaTarefaDto.getId());
		TarefaDto tarefaDtoEncontrada =  tarefaEncontrada.getBody();

		// Asserts
		// verifica se a tarefa foi encontrada (not null)
		assertNotNull(tarefaEncontrada);

		// verifica se os campos estão corretos
		assertEquals(novaTarefaDto.getId(), tarefaDtoEncontrada.getId());
		assertEquals("Tarefa Teste", tarefaDtoEncontrada.getTitulo());
		// verifica se uma tarefa não existente retorna uma exceção
		assertThrows(TarefaNaoEncontradaException.class, () -> {
			tarefaService.buscarTarefa(1000L);
		});
	}

	@Test
	void deveAtualizarStatus() {
		// cria/adiciona uma tarefa ao banco de dados
		ResponseEntity<TarefaDto> novaTarefa = tarefaService.criarTarefa(tarefa);
		novaTarefaDto =  novaTarefa.getBody();

		// instancia uma nova tarefa com o status diferente
		Tarefa tarefaComStatusAtualizado = new Tarefa();
		tarefaComStatusAtualizado.setStatus("CONCLUIDA");

		// atualiza o status da tarefa
		ResponseEntity<TarefaDto> tarefaAtualizada =  tarefaService.atualizarStatus(novaTarefaDto.getId(), tarefaComStatusAtualizado);
		TarefaDto tarefaAtualizadaDto =  tarefaAtualizada.getBody();

		// Asserts
		// verifica se o status da tarefa foi atualizado
		assertEquals("CONCLUIDA", tarefaAtualizadaDto.getStatus());

		// verifica se uma tarefa não existente retorna uma exceção
		assertThrows(TarefaNaoEncontradaException.class, () -> {
			tarefaService.atualizarStatus(1000L, tarefaComStatusAtualizado);
		});
	}

	@Test
	void deveDeletarTarefa() {
        // cria/adiciona uma tarefa ao banco de dados
		ResponseEntity<TarefaDto> novaTarefa = tarefaService.criarTarefa(tarefa);
		novaTarefaDto =  novaTarefa.getBody();

		// Asserts
        // verifica se a tarefa foi adicionada no banco de dados
		var listaDeTarefas1 = tarefaService.listarTarefas();
		assertEquals(1, listaDeTarefas1 .size());

		// exclui a tarefa
		tarefaService.excluirTarefa(novaTarefaDto.getId());

		// verifica se a tarefa foi excluída no banco de dados
		var listaDeTarefas2 = tarefaService.listarTarefas();
		assertEquals(0, listaDeTarefas2 .size());

		// verifica se uma tarefa não existente retorna uma exceção
		assertThrows(TarefaNaoEncontradaException.class, () -> {
			tarefaService.excluirTarefa(1000L);
		});
	}

}
