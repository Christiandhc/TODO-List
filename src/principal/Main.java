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
    private static final GerenciadorTarefas gerenciador = new GerenciadorTarefas();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Tente novamente.");
            scanner.nextLine();
        }
        return opcao;
    }

    private static void adicionarTarefa() {
        System.out.print("Nome da tarefa: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição da tarefa: ");
        String descricao = scanner.nextLine();

        System.out.print("Data de término (DD-MM-AAAA): ");
        LocalDate dataTermino;
        try {
            String dataInput = scanner.nextLine();
            dataTermino = LocalDate.parse(dataInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            System.out.println("Data inválida. Usando a data atual.");
            dataTermino = LocalDate.now();
        }

        System.out.print("Prioridade (1 a 5): ");
        int prioridade = 1;
        try {
            prioridade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Prioridade inválida. Usando prioridade 1.");
        }
        scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Status (TODO, DOING, DONE): ");
        Status status = Status.TODO;
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
        int prioridade = 1;
        try {
            prioridade = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Prioridade inválida. Usando prioridade 1.");
        }
        scanner.nextLine();
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
        Status status = Status.TODO;
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

        Tarefa tarefa = gerenciador.buscarTarefa(nome);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("Deseja atualizar a descrição atual: " + tarefa.getDescricao() + "? (S/N)");
        String resposta = scanner.nextLine();
        String descricao = tarefa.getDescricao();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Nova descrição da tarefa: ");
            descricao = scanner.nextLine();
        }

        System.out.println("Deseja atualizar a data de término atual: " + tarefa.getDataTermino().format(formatter) + "? (S/N)");
        resposta = scanner.nextLine();
        LocalDate dataTermino = tarefa.getDataTermino();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Nova data de término (dd-MM-yyyy): ");
            dataTermino = LocalDate.parse(scanner.nextLine(), formatter);
        }

        System.out.println("Deseja atualizar a prioridade atual: " + tarefa.getPrioridade() + "? (S/N)");
        resposta = scanner.nextLine();
        int prioridade = tarefa.getPrioridade();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Nova prioridade (1 a 5): ");
            prioridade = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
        }

        System.out.println("Deseja atualizar a categoria atual: " + tarefa.getCategoria() + "? (S/N)");
        resposta = scanner.nextLine();
        String categoria = tarefa.getCategoria();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Nova categoria: ");
            categoria = scanner.nextLine();
        }

        System.out.println("Deseja atualizar o status atual: " + tarefa.getStatus() + "? (S/N)");
        resposta = scanner.nextLine();
        Status status = tarefa.getStatus();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Novo status (TODO, DOING, DONE): ");
            status = Status.valueOf(scanner.nextLine().toUpperCase());
        }

        Tarefa tarefaAtualizada = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);

        if (gerenciador.atualizarTarefa(nome, tarefaAtualizada)) {
            System.out.println("Tarefa atualizada com sucesso.");
        } else {
            System.out.println("Erro ao atualizar a tarefa.");
        }
    }

}

