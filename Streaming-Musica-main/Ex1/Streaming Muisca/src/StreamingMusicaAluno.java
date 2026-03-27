import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 * VERSÃO ALUNO
 *
 * Complete as partes marcadas com TODO.
 */
public class StreamingMusicaAluno {

    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();

    // Gêneros válidos
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming! Até logo! 🎵");
        scanner.close();
    }

    /**
     * FORNECIDO: Exibe o menu principal
     */
    public static void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎵 SISTEMA DE STREAMING DE MÚSICA 🎵");
        System.out.println("=".repeat(50));
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
        System.out.print("Escolha uma opção: ");
    }

    /**
     * FORNECIDO: Lê a opção do usuário com tratamento de erro
     */
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * FORNECIDO: Processa a opção escolhida
     */
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarPorTitulo(); break;
            case 4: buscarPorArtista(); break;
            case 5: buscarPorGenero(); break;
            case 6: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida! Tente novamente.");
        }
    }

    /**
     * FORNECIDO: Cadastra uma nova música
     */
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("❌ Título não pode ser vazio!");
            return;
        }

        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();

        if (artista.isEmpty()) {
            System.out.println("❌ Artista não pode ser vazio!");
            return;
        }

        System.out.print("Duração (em segundos): ");
        int duracao;
        try {
            duracao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Duração inválida!");
            return;
        }

        if (duracao <= 0) {
            System.out.println("❌ Duração deve ser maior que 0!");
            return;
        }

        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
        }

        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");
        int opcaoGenero;
        try {
            opcaoGenero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Opção inválida!");
            return;
        }

        if (opcaoGenero < 1 || opcaoGenero > GENEROS_VALIDOS.length) {
            System.out.println("❌ Gênero inválido!");
            return;
        }

        String genero = GENEROS_VALIDOS[opcaoGenero - 1];

        titulos.add(titulo);
        artistas.add(artista);
        duracoes.add(duracao);
        generos.add(genero);

        System.out.println("✅ Música cadastrada com sucesso!");
    }

    /**
     * FORNECIDO: Lista todas as músicas
     */
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");

        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }

        for (int i = 0; i < titulos.size(); i++) {
            System.out.printf("%d. Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                    (i + 1),
                    titulos.get(i),
                    artistas.get(i),
                    formatarDuracao(duracoes.get(i)),
                    generos.get(i)
            );
        }

        System.out.println("\nTotal: " + titulos.size() + " música(s)");
    }

    /**
     * FORNECIDO: Busca músicas por título
     */
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");

        System.out.print("Digite o título (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).toLowerCase().contains(busca)) {
                if (!encontrou) {
                    System.out.println("\nMúsicas encontradas:");
                    encontrou = true;
                }
                System.out.printf("- %s | %s | %s | %s%n",
                        titulos.get(i),
                        artistas.get(i),
                        formatarDuracao(duracoes.get(i)),
                        generos.get(i)
                );
            }
        }

        if (!encontrou) {
            System.out.println("❌ Nenhuma música encontrada com esse título.");
        }
    }

    /**
     * TODO: ALUNO IMPLEMENTA - Busca músicas por artista
     * Dica: Similar à busca por título
     */
    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---");
        // TODO: Solicitar nome do artista

        System.out.print("Digite o nome do artista (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        int numTitulos = 0;
        boolean encontrado = false;

        try {
            for (int i = 0; i < artistas.size(); i++) {

                if (artistas.get(i).toLowerCase().contains(busca)) {
                    if (!encontrado) {
                        System.out.println("-- Músicas:");
                        encontrado = true;
                    }

                    numTitulos++;
                    System.out.println(numTitulos + ". Títulos: " + titulos.get(i) +
                            " | Duração: " + formatarDuracao(duracoes.get(i)) +
                            " | Gênero: " + generos.get(i));
                }


            }
        } catch (Exception e) {
            System.out.println("-- Ocorreu um erro inesperado.");
        }

        if (!encontrado) {
            System.out.println("Artista não encontrado.");
        }
    }

    /**
     * TODO: ALUNO IMPLEMENTA - Busca músicas por gênero
     */
    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---");

        // TODO: Exibir gêneros disponíveis
        System.out.println("Gêneros disponíveis:");
        for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS_VALIDOS[i]);
        }

        // TODO: Solicitar escolha do gênero
        System.out.print("Escolha o gênero (1-" + GENEROS_VALIDOS.length + "): ");

        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            escolha = escolha-1;
            boolean encontrado = false;

            for (String genero : GENEROS_VALIDOS) {
                if (genero.toLowerCase().contains(GENEROS_VALIDOS[escolha].toLowerCase())) {
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                int numTitulos = 0;
                System.out.println("-- Músicas:");
                for (int i = 0; i < artistas.size(); i++) {
                    if (generos.get(i).toLowerCase().contains(GENEROS_VALIDOS[escolha].toLowerCase())) {
                        numTitulos++;
                        System.out.println(numTitulos + ". Título: " + titulos.get(i) + " | Duração: " + formatarDuracao(duracoes.get(i)) + " | Artista: " + artistas.get(i));
                    }
                }

            } else {
                System.out.println("Não encontrado.");
            }


        } catch (NumberFormatException e) {
            System.out.println("-- Escolha inválida.");
        } catch (Exception e) {
            System.out.println("-- Ocorreu um erro inesperado.");
        }

    }


    public static String pegarGeneroComum() {
        int[] contagem = new int[GENEROS_VALIDOS.length];

        // Itera sobre o array dos gêneros q foram colocados
        for (String generoLista : generos) {
            // Itera sobre o tamanho dos gêneros válidos e verifica se o generoLista que está iterando sobre generos q foram colocados é igual a posição i dos generos válidos
            // se for, ele adiciona mais na posição do array contagem q tem o msm tamanho de generos válidos
            for (int i = 0; i < GENEROS_VALIDOS.length; i++) {
                if (generoLista.toLowerCase().contains(GENEROS_VALIDOS[i].toLowerCase())) {
                    contagem[i]++;
                    break;
                }
            }
        }

        int indiceMaior = 0;
        for (int i = 1; i < contagem.length; i++) {
            if (contagem[i] > contagem[indiceMaior]) {
                indiceMaior = i;
            }
        }

        return contagem[indiceMaior] > 0 ? GENEROS_VALIDOS[indiceMaior] : null;
    }

    /**
     * TODO: ALUNO IMPLEMENTA - Exibe estatísticas do sistema
     */
    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");

        if (titulos.isEmpty()) {
            System.out.println("Nenhuma música cadastrada ainda.");
            return;
        }

        // TODO: Calcular total de músicas
        int totalMusicas = titulos.size();

        // TODO: Calcular duração total (somar todas as durações)
        int duracaoTotal = 0;
        for (Integer d : duracoes) {
            duracaoTotal += d;
        }


        // TODO: Calcular duração média
        int duracaoMedia = duracaoTotal / totalMusicas;

        // TODO: Encontrar gênero mais comum (usar método auxiliar)
        String generoComum = pegarGeneroComum();

        // TODO: Exibir estatísticas
        System.out.println("Total de músicas: " + totalMusicas);
        System.out.println("Duração total: " + duracaoTotal);
        System.out.println("Duração média: " + duracaoMedia);
        System.out.println("Gênero comum: " + generoComum);
    }

    /**
     * FORNECIDO: Formata duração de segundos para MM:SS
     */
    public static String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }

    /**
     * FORNECIDO: Adiciona músicas de teste
     */
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");

        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");

        titulos.add("Smells Like Teen Spirit");
        artistas.add("Nirvana");
        duracoes.add(301);
        generos.add("Rock");
    }
}