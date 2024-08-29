package principal;

import molde.Tarefa;
import molde.Status;
import controle.GerenciadorTarefas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/*---------------------------------------------------------------------*
 *         Classe principal que executa a aplicação TODO list.         *
 * Fornece um menu simples no terminal para interagir com o usuário.   *
 *---------------------------------------------------------------------*/
public class Main {
    private static GerenciadorTarefas gerenciador = new GerenciadorTarefas();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = obterOpcao();

            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    removerTarefa();
                    break;
                case 3:
                    listarTarefas();
                    break;
                case 4:
                    listarTarefasPorCategoria();
                    break;
                case 5:
                    listarTarefasPorPrioridade();
                    break;
                case 6:
                    listarTarefasPorStatus();
                    break;
                case 7:
                    consultarNumeroAtividades();
                    break;
                case 8:
                    atualizarTarefa();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Remover Tarefa");
        System.out.println("3. Listar Tarefas");
        System.out.println("4. Listar Tarefas por Categoria");
        System.out.println("5. Listar Tarefas por Prioridade");
        System.out.println("6. Listar Tarefas por Status");
        System.out.println("7. Consultar Número de Atividades");
        System.out.println("8. Atualizar Tarefa");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int obterOpcao() {
        int opcao = -1;
        try {
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpando buffer de entrada para remover quebra de linha pendente.
        } catch (Exception e) {
            System.out.println("Entrada inválida. Tente novamente.");
            scanner.nextLine();  // Limpa a entrada inválida
        }
        return opcao;
    }

    private static void adicionarTarefa() {
        System.out.print("Nome da tarefa: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.print("Data de término (DD-MM-AAAA): ");
        LocalDate dataTermino = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        try {
            dataTermino = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Data inválida. Usando a data atual.");
        }
        System.out.print("Prioridade (1 a 5): ");
        int prioridade = 1; // Define uma prioridade padrão
        try {
            prioridade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Prioridade inválida. Usando prioridade 1.");
        }
        scanner.nextLine();  // Consome a quebra de linha
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Status (TODO, DOING, DONE): ");
        Status status = Status.TODO; // Define um status padrão
        try {
            status = Status.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido. Usando TODO.");
        }

        Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
        gerenciador.adicionarTarefa(tarefa);
        System.out.println("Tarefa adicionada com sucesso.");
    }

    private static void removerTarefa() {
        System.out.print("Nome da tarefa a ser removida: ");
        String nome = scanner.nextLine();
        if (gerenciador.removerTarefa(nome)) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void listarTarefas() {
        List<Tarefa> tarefas = gerenciador.listarTarefas();
        for (Tarefa t : tarefas) {
            System.out.println(t.getNome() + " | " + t.getDescricao() + " | " + t.getDataTermino().format(formatter) + " | " +
                    t.getPrioridade() + " | " + t.getCategoria() + " | " + t.getStatus());
        }
    }

    private static void listarTarefasPorCategoria() {
        System.out.print("Categoria para listar: ");
        String categoria = scanner.nextLine();
        List<Tarefa> tarefas = gerenciador.listarTarefas();
        for (Tarefa t : tarefas) {
            if (t.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(t.getNome() + " | " + t.getDescricao() + " | " + t.getDataTermino() + " | " +
                        t.getPrioridade() + " | " + t.getCategoria() + " | " + t.getStatus());
            }
        }
    }

    private static void listarTarefasPorPrioridade() {
        System.out.print("Prioridade para listar (1 a 5): ");
        int prioridade = 1; // Define uma prioridade padrão
        try {
            prioridade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Prioridade inválida. Usando prioridade 1.");
        }
        scanner.nextLine();  // Consome a quebra de linha
        List<Tarefa> tarefas = gerenciador.listarTarefas();
        for (Tarefa t : tarefas) {
            if (t.getPrioridade() == prioridade) {
                System.out.println(t.getNome() + " | " + t.getDescricao() + " | " + t.getDataTermino() + " | " +
                        t.getPrioridade() + " | " + t.getCategoria() + " | " + t.getStatus());
            }
        }
    }

    private static void listarTarefasPorStatus() {
        System.out.print("Status para listar (TODO, DOING, DONE): ");
        Status status = Status.TODO; // Define um status padrão
        try {
            status = Status.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido. Usando TODO.");
        }
        List<Tarefa> tarefas = gerenciador.listarTarefas();
        for (Tarefa t : tarefas) {
            if (t.getStatus() == status) {
                System.out.println(t.getNome() + " | " + t.getDescricao() + " | " + t.getDataTermino() + " | " +
                        t.getPrioridade() + " | " + t.getCategoria() + " | " + t.getStatus());
            }
        }
    }

    private static void consultarNumeroAtividades() {
        long countTodo = gerenciador.listarTarefas().stream().filter(t -> t.getStatus() == Status.TODO).count();
        long countDoing = gerenciador.listarTarefas().stream().filter(t -> t.getStatus() == Status.DOING).count();
        long countDone = gerenciador.listarTarefas().stream().filter(t -> t.getStatus() == Status.DONE).count();

        System.out.println("Número de atividades:");
        System.out.println("TODO: " + countTodo);
        System.out.println("DOING: " + countDoing);
        System.out.println("DONE: " + countDone);
    }
    private static void atualizarTarefa() {
        System.out.print("Nome da tarefa a ser atualizada: ");
        String nome = scanner.nextLine();

        // Coleta os novos dados para a tarefa
        System.out.print("Nova descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.print("Nova data de término (dd-MM-yyyy): ");
        LocalDate dataTermino = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Nova prioridade (1 a 5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine();  // Consome a quebra de linha
        System.out.print("Nova categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Novo status (TODO, DOING, DONE): ");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        Tarefa tarefaAtualizada = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);

        if (gerenciador.atualizarTarefa(nome, tarefaAtualizada)) {
            System.out.println("Tarefa atualizada com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
}

