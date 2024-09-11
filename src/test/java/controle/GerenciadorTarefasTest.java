package controle;

import molde.Status;
import molde.Tarefa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorTarefasTest {
    private GerenciadorTarefas gerenciador;

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorTarefas();
        gerenciador.removerTarefa("tarefa1");
    }

    @AfterEach
    void tearDown() {
        gerenciador.removerTarefa("tarefa1");
    }

    @Test
    void testAdicionarTarefa() {
        Tarefa tarefa = new Tarefa("tarefa1", "descricao1", LocalDate.now(), 1, "categoria1", Status.TODO);
        gerenciador.adicionarTarefa(tarefa);
        assertEquals(1, gerenciador.listarTarefas().size(), "Número de tarefas deveria ser 1");
        System.out.println("Teste de adição de tarefa: Criada com Sucesso!");
    }

    @Test
    void testRemoverTarefa() {
        Tarefa tarefa = new Tarefa("tarefa1", "descricao1", LocalDate.now(), 1, "categoria1", Status.TODO);
        gerenciador.adicionarTarefa(tarefa);
        assertTrue(gerenciador.removerTarefa("tarefa1"), "A tarefa deveria ser removida com sucesso");
        assertFalse(gerenciador.removerTarefa("tarefa1"), "A tarefa não deveria ser encontrada");
        System.out.println("Teste de remoção de tarefa: Removida com Sucesso!");
    }

    @Test
    void testAtualizarTarefa() {
        Tarefa tarefa = new Tarefa("tarefa1", "descricao1", LocalDate.now(), 1, "categoria1", Status.TODO);
        gerenciador.adicionarTarefa(tarefa);
        Tarefa tarefaAtualizada = new Tarefa("tarefa1", "descricaoAtualizada", LocalDate.now().plusDays(1), 2, "categoriaAtualizada", Status.DOING);
        assertTrue(gerenciador.atualizarTarefa("tarefa1", tarefaAtualizada), "A tarefa deveria ser atualizada com sucesso");
        Tarefa tarefaBuscada = gerenciador.buscarTarefa("tarefa1");
        assertNotNull(tarefaBuscada, "A tarefa buscada não deveria ser nula");
        assertEquals("descricaoAtualizada", tarefaBuscada.getDescricao(), "Descrição da tarefa deveria ser atualizada");
        System.out.println("Teste de atualização de tarefa: Atualizada com Sucesso!");
    }
}
