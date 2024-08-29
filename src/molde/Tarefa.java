package molde;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    // Atributos privados que definem as características de uma tarefa.
    private String nome; // Nome da tarefa.
    private String descricao; // Descrição detalhada da tarefa.
    private LocalDate dataTermino; // Data de término da tarefa.
    private int prioridade; // Nível de prioridade (1 a 5).
    private String categoria; // Categoria da tarefa (ex.: Trabalho, Pessoal).
    private Status status; // Status da tarefa (TODO, DOING, DONE).

    // Construtor que inicializa os atributos da tarefa
    public Tarefa(String nome, String descricao, LocalDate dataTermino, int prioridade, String categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    // Métodos getters e setters para acessar e modificar os atributos.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Método sobrescrito para representar a tarefa como uma string legível.
    @Override
    public String toString() {
        // Define o formato da data para "dd-MM-yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format(
                "Tarefa{nome='%s', descricao='%s', dataTermino=%s, prioridade=%d, categoria='%s', status=%s}",
                nome, descricao, dataTermino.format(formatter), prioridade, categoria, status
        );
    }
}

