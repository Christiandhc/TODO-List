package molde;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Tarefa {

    private String nome;
    private String descricao;
    private LocalDate dataTermino;
    private int prioridade;
    private String categoria;
    private Status status;

    public Tarefa(String nome, String descricao, LocalDate dataTermino, int prioridade, String categoria, Status status) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

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

    public void atualizarTarefa() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Selecione o campo que deseja atualizar:");
            System.out.println("1 - Nome");
            System.out.println("2 - Descrição");
            System.out.println("3 - Data de Término (formato dd-MM-yyyy)");
            System.out.println("4 - Prioridade");
            System.out.println("5 - Categoria");
            System.out.println("6 - Status (TO_DO, DOING, DONE)");
            System.out.println("7 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    setNome(novoNome);
                    System.out.println("Nome atualizado.");
                    break;
                case 2:
                    System.out.print("Digite a nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    setDescricao(novaDescricao);
                    System.out.println("Descrição atualizada.");
                    break;
                case 3:
                    System.out.print("Digite a nova data de término (formato dd-MM-yyyy): ");
                    String novaData = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate novaDataTermino = LocalDate.parse(novaData, formatter);
                        setDataTermino(novaDataTermino);
                        System.out.println("Data de término atualizada.");
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido.");
                    }
                    break;
                case 4:
                    System.out.print("Digite a nova prioridade: ");
                    int novaPrioridade = scanner.nextInt();
                    setPrioridade(novaPrioridade);
                    System.out.println("Prioridade atualizada.");
                    break;
                case 5:
                    System.out.print("Digite a nova categoria: ");
                    String novaCategoria = scanner.nextLine();
                    setCategoria(novaCategoria);
                    System.out.println("Categoria atualizada.");
                    break;
                case 6:
                    System.out.print("Digite o novo status (TO_DO, DOING, DONE): ");
                    String novoStatus = scanner.nextLine().toUpperCase();
                    try {
                        setStatus(Status.valueOf(novoStatus));
                        System.out.println("Status atualizado.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Status inválido. Use TO_DO, DOING ou DONE.");
                    }
                    break;
                case 7:
                    sair = true;
                    System.out.println("Atualização concluída.");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // Método sobrescrito para representar a tarefa como uma string legível.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format(
                "Tarefa{nome='%s', descricao='%s', dataTermino=%s, prioridade=%d, categoria='%s', status=%s}",
                nome, descricao, dataTermino.format(formatter), prioridade, categoria, status
        );
    }
}
