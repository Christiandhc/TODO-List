package molde;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void testCriacaoTarefa() {
        LocalDate dataTermino = LocalDate.of(2024, 12, 31);
        Tarefa tarefa = new Tarefa("tarefa1", "descricao1", dataTermino, 3, "categoria1", Status.TODO);
        assertEquals("tarefa1", tarefa.getNome(), "Nome da tarefa deveria ser 'tarefa1'");
        assertEquals("descricao1", tarefa.getDescricao(), "Descrição da tarefa deveria ser 'descricao1'");
        assertEquals(dataTermino, tarefa.getDataTermino(), "Data de término deveria ser 31-12-2024");
        assertEquals(3, tarefa.getPrioridade(), "Prioridade da tarefa deveria ser 3");
        assertEquals("categoria1", tarefa.getCategoria(), "Categoria da tarefa deveria ser 'categoria1'");
        assertEquals(Status.TODO, tarefa.getStatus(), "Status da tarefa deveria ser TODO");
        System.out.println("Teste de criação de tarefa: Criada com Sucesso!");
    }
}
