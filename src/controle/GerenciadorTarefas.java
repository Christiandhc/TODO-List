package controle;

import molde.Tarefa;
import molde.Status;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


/*------------------------------------------------------------*
 *      Classe responsável por gerenciar as tarefas.          *
 *                                                            *
 * Ela contém métodos necessários para lidar com as tarefas.  *
 *                                                            *
 *------------------------------------------------------------*/
public class GerenciadorTarefas {

    // Lista que armazena todas as tarefas em memória
    private List<Tarefa> tarefas;

    // Nome do arquivo onde as tarefas serão persistidas
    private static final String NOME_ARQUIVO = "tarefas.csv";

    // Construtor que inicializa a lista de tarefas e carrega as tarefas salvas
    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();
        carregarTarefas();
    }

    /**
     * Método para adicionar uma nova tarefa ao repositório.
     * A tarefa é inserida na posição correta com base na prioridade.
     *
     * @param tarefa A tarefa a ser adicionada.
     */
    public void adicionarTarefa(Tarefa tarefa) {
        int posicaoInserir = 0;
        for (Tarefa t : tarefas) {
            if (t.getPrioridade() > tarefa.getPrioridade()) {
                break;
            }
            posicaoInserir++;
        }
        tarefas.add(posicaoInserir, tarefa);
        salvarTarefas();
    }

    /**
     * Método para remover uma tarefa do repositório.
     *
     * @param nome Nome da tarefa a ser removida.
     * @return true se a tarefa foi removida com sucesso, false caso contrário.
     */
    public boolean removerTarefa(String nome) {
        for (Tarefa t : tarefas) {
            if (t.getNome().equals(nome)) {
                tarefas.remove(t);
                salvarTarefas();
                return true;
            }
        }
        return false;
    }

    /**
     * Método para listar todas as tarefas.
     *
     * @return Uma lista de todas as tarefas.
     */
    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    //Método para atualizar as tarefas

    public boolean atualizarTarefa(String nome, Tarefa tarefaAtualizada) {
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa t = tarefas.get(i);
            if (t.getNome().equals(nome)) {
                tarefas.set(i, tarefaAtualizada);
                salvarTarefas();
                return true;
            }
        }
        return false;
    }


    /**
     * Método para salvar as tarefas no arquivo CSV.
     * Cada tarefa é salva em uma linha separada, com seus atributos separados por vírgulas.
     */
    private void salvarTarefas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefa t : tarefas) {
                String linha = String.format("%s,%s,%s,%d,%s,%s",
                        t.getNome(),
                        t.getDescricao(),
                        t.getDataTermino(),
                        t.getPrioridade(),
                        t.getCategoria(),
                        t.getStatus());
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    /**
     * Método para carregar tarefas do arquivo CSV.
     * As tarefas são lidas e adicionadas à lista de tarefas.
     */
    private void carregarTarefas() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return; // Arquivo não existe, nada para carregar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 6) {
                    String nome = partes[0];
                    String descricao = partes[1];
                    LocalDate dataTermino = LocalDate.parse(partes[2]);
                    int prioridade = Integer.parseInt(partes[3]);
                    String categoria = partes[4];
                    Status status = Status.valueOf(partes[5]);

                    Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
                    tarefas.add(tarefa);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }
}
