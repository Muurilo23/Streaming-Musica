import java.util.InputMismatchException;
import java.util.Scanner;

public class ControleEstoque {

    private static Produto[] produtos = new Produto[100];
    private static int totalProdutos = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        while (true) {

            try {
                exibirMenu();
                System.out.print("\n-> Sua escolha: ");
                opcao = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("--- Digite apenas números (1-4).\n");
                scan.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarProduto(scan);
                    break;
                case 2:
                    consultarEstoque();
                    break;
                case 3:
                    registrarVenda(scan);
                    break;
                case 4:
                    System.out.println("--- Saindo... ---");
                    return;
                default:
                    System.out.println("--- Escolha uma opção válida (1-4).\n");
            }

        }


    }

    private static void exibirMenu() {
        System.out.println("BEM VINDO AO SISTEMA DE CONTROLE DE ESTOQUE");
        System.out.println("Escolha uma opção abaixo:");
        System.out.println("\n1. Cadastrar Novo Produto");
        System.out.println("2. Consultar Estoque");
        System.out.println("3. Registrar Venda");
        System.out.println("4. Sair");
    }

    private static void cadastrarProduto(Scanner scanner) {
        try {
            System.out.println("============================");
            System.out.print("1. Informe o nome do produto: ");
            String nomeProduto = scanner.nextLine();
            System.out.println();

            System.out.print("2. Informe a quantidade: ");
            int quantidadeProduto = scanner.nextInt();
            System.out.println();
            scanner.nextLine();

            if (quantidadeProduto <= 0) {
                System.out.println("--- Quantidade inválida.");
                System.out.println("============================\n");
                return;
            }

            System.out.print("3. Informe o preço: ");
            double precoProduto = scanner.nextDouble();
            System.out.println();
            scanner.nextLine();

            if (precoProduto <= 0) {
                System.out.println("--- Preço inválido.");
                System.out.println("============================\n");
                return;
            }

            Produto produto = new Produto(nomeProduto, quantidadeProduto, precoProduto);

            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] == null) {
                    produtos[i] = produto;
                    break;
                }
            }

            totalProdutos++;
            System.out.println("--- Produto Cadastrado com Sucesso ---");
            System.out.println("============================");
        } catch (InputMismatchException e) {
            System.out.println("\n--- Coloque as informações nos campos corretos!");
            System.out.println("============================");
            scanner.nextLine();

            return;
        }
    }

    private static void consultarEstoque() {
        System.out.println("============================");
        if (totalProdutos == 0) {
            System.out.println("--- Nenhum produto cadastrado, o estoque está vazio.");
            System.out.println("============================\n");
            return;
        }

        System.out.println("PRODUTO | QUANTIDADE | PREÇO");

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null) {
                System.out.println(produtos[i].getNome() + " | " + produtos[i].getQuantidade() + " | R$: " + produtos[i].getPreco());
            }
        }

        System.out.println("\n--- TOTAL PRODUTOS: " + totalProdutos);
        System.out.println("============================");

    }

    private static void registrarVenda(Scanner scanner) {
        if (totalProdutos == 0) {
            System.out.println("============================");
            System.out.println("--- Nenhum produto cadastrado, o estoque está vazio.");
            System.out.println("============================\n");
            return;
        }

        try {

            consultarEstoque();

            System.out.print("1. Informe o nome do produto: ");
            String nomeProduto = scanner.nextLine();
            System.out.println();

            System.out.print("2. Informe a quantidade: ");
            int quantidadeProduto = scanner.nextInt();
            System.out.println();
            scanner.nextLine();

            if (quantidadeProduto <= 0) {
                System.out.println("--- Quantidade inválida.");
                System.out.println("============================\n");
                return;
            }

            boolean encontrado = false;
            for (int i = 0; i < produtos.length; i++) {
                if (produtos[i] != null && produtos[i].getNome().equals(nomeProduto)) {
                    if (produtos[i].getQuantidade() < quantidadeProduto) {
                        System.out.println("--- Não há estoque suficiente.");
                        System.out.println("============================\n");
                        return;
                    }

                    produtos[i].setQuantidade(produtos[i].getQuantidade() - quantidadeProduto);
                    encontrado = true;
                    System.out.println("--- Produto Vendido ---");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("--- O produto não foi encontrado.");
            }

            System.out.println("============================");

        } catch (InputMismatchException e) {
            System.out.println("\n--- Preencha os campos corretamente!");
            System.out.println("============================");
            scanner.nextLine();
            return;
        }

    }


}