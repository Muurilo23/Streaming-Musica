import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();


    public static void main(String[] args) {
        Playlist p1 = new Playlist("Minha primeira Playlist");
        Playlist p2 = new Playlist("Escutar em dia de Chuva");
        Playlist p3 = new Playlist("Estudando");
        Playlist p4 = new Playlist("Treinando");
        Musica m1 = new Musica("Acabou tudo", "Ronaldinho", 120, "Pop");
        Musica m2 = new Musica("É isso", "Beatriz", 190, "Hip-hop");
        Musica m3 = new Musica("Tudo muda", "Charlie Brown", 120, "Hip-hop");
        musicas.add(m1);
        musicas.add(m2);
        musicas.add(m3);
        usuario.adicionarPlaylist(p1);
        usuario.adicionarPlaylist(p2);
        usuario.adicionarPlaylist(p3);
        usuario.adicionarPlaylist(p4);
        usuario.getPlaylist(0).adicionarMusica(m1);
        usuario.getPlaylist(0).adicionarMusica(m2);
        usuario.getPlaylist(0).adicionarMusica(m3);

        usuario.getPlaylist(1).adicionarMusica(m3);


        int opcao;
        do {
            menu();
            opcao = lerOpcao();
            processarEscolha(opcao);

        } while (opcao != 0);
    }

    public static void processarEscolha(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarMusicaPorNome();
                break;
            case 4:
                criarPlaylist();
                break;
            case 5:
                gerenciarPlaylist();
                break;
            case 6:
                exibirEstatisticas();
                break;
            case 0:
                System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming! Até logo! 🎵\n");
                break;
            default:
                System.out.println("❌ Opção inválida");
                break;
        }
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void menu() {
        System.out.println("-".repeat(20));
        System.out.println("=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.println("-".repeat(20));
        System.out.print("Escolha uma opção: ");

    }

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

        Musica musica = new Musica(titulo, artista, duracao, genero);
        musicas.add(musica);
        System.out.println("✅ Música cadastrada com sucesso!");
    }


    public static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há músicas disponiveis.");
            return;
        }

        System.out.println("\nPOSIÇÃO | MÚSICAS | ARTISTA | GÊNERO | DURAÇÃO");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i+1) + " | "
                    + musicas.get(i).getTitulo() + " | "
                    + musicas.get(i).getArtista() + " | "
                    + musicas.get(i).getGenero() + " | "
                    + formatarDuracao((int) (musicas.get(i).getDuracaoSegundos())));
        }
        System.out.println();

    }

    public static void buscarMusicaPorNome() {
        if (musicas.isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há músicas disponiveis.");
            return;
        }
        System.out.println("-".repeat(20));
        System.out.print("Informe uma parte ou o nome completo: ");
        String nomeMusica = scanner.nextLine();

        for (Musica musica : musicas) {
            if (musica.getTitulo().toLowerCase().contains(nomeMusica.toLowerCase())) {
                System.out.println("✅ Música encontrada!");
                musica.exibir();
                return;
            }
        }
        System.out.println("❌ Música não foi encontrada.");
    }

    public static void criarPlaylist() {
        if (musicas.isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há músicas disponiveis.");
            return;
        }

        System.out.println("-".repeat(20));

        System.out.println("Informe o nome da playlist: ");
        String nomePlaylist = scanner.nextLine();

        Playlist playlist = new Playlist(nomePlaylist);
        usuario.adicionarPlaylist(playlist);
        System.out.println("✅ Playlist criada!");

    }

    public static void gerenciarPlaylist() {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há playlists disponiveis.");
            return;
        }

        int opcao;

        do {
            System.out.println("-".repeat(20));
            System.out.println("=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("0. Voltar");
            System.out.println("-".repeat(20));
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();
            processarEscolhaGerenciarPlaylist(opcao);
        } while (opcao != 0);
    }

    public static void listarPlayList() {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há playlists disponiveis.");
            return;
        }

        usuario.listarPlaylist();
    }

    public static void adicionarMusica() {
        System.out.println("-".repeat(20));
        listarPlayList();
        int numPlaylist;
        int numMusica;

        try {
            System.out.println("Informe a posição da Playlist: ");
            numPlaylist = scanner.nextInt();
            Playlist playlist = usuario.getPlaylist(numPlaylist-1);

            listarMusicas();
            System.out.print("Informe a posição da Música: ");
            numMusica = scanner.nextInt();
            scanner.nextLine();

            Musica musica = musicas.get(numMusica-1);
            playlist.adicionarMusica(musica);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Por favor, preencha o campo corretamente.");
        }

    }

    public static void removerMusica() {
        listarPlayList();
        int numPlaylist;
        int musicaRemover = 0;
        boolean verificandoSeTemMusica;

        try {
            // Pegando a playlist
            System.out.print("Informe a posição da Playlist: ");
            numPlaylist = scanner.nextInt();
            Playlist playlist = usuario.getPlaylist(numPlaylist-1);
            scanner.nextLine();

            // Se tiver musica, ele retorna true, se não, false
            verificandoSeTemMusica = usuario.getPlaylist(numPlaylist-1).temMusica();

            if (verificandoSeTemMusica) {
                playlist.listarMusicas(); // Listando as músicas da playlist
                System.out.print("Escolha a posição da música para remover: ");

                try {
                    musicaRemover = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("❌ Por favor, preencha o campo corretamente.");
                }

                Musica musica = playlist.getMusicas().get(musicaRemover-1);
                scanner.nextLine();

                playlist.removerMusica(musica);
                System.out.println("✅ Música removida!");
            } else {
                System.out.println("--------------------");
                System.out.println("❌ Essa Playlist está vazia.");
            }


        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Por favor, preencha o campo corretamente.");
        }

    }

    public static void exibirDetalhesDeUmaPlaylist() {
        try {
            System.out.println("-".repeat(20));
            System.out.print("Informe uma parte ou o nome completo da playlist: ");
            String nomePlaylist = scanner.nextLine();

            for (Playlist playlist : usuario.getPlaylists()) {
                if (playlist.getNome().toLowerCase().contains(nomePlaylist.toLowerCase())) {
                    System.out.println("\n---");
                    System.out.println("🎶 PLAYLIST: ");
                    System.out.println("NOME: " + playlist.getNome() + " | QUANT. MÚSICAS: "
                            + playlist.getQuantidadeMusicas() + " | DURAÇÃO TOTAL: "
                            + formatarDuracao((int) (playlist.getDuracaoTotal())));

                    System.out.println();

                    if (playlist.temMusica()) {
                        System.out.println("🎧 MÚSICAS DA PLAYLIST: ");
                        System.out.println("MÚSICA | ARTISTA | GÊNERO | DURAÇÃO");
                        for (Musica musica : playlist.getMusicas()) {
                            System.out.println(musica.getTitulo() + " | " + musica.getArtista() + " | " + musica.getGenero() + " | " + formatarDuracao((int) (musica.getDuracaoSegundos())));
                        }
                    }

                }

            }


        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Por favor, preencha o campo corretamente.");
        }
    }

    public static void processarEscolhaGerenciarPlaylist(int opcao) {
        switch (opcao) {
            case 1:
                listarPlayList();
                break;
            case 2:
                adicionarMusica();
                break;
            case 3:
                removerMusica();
                break;
            case 4:
                exibirDetalhesDeUmaPlaylist();
                break;
            case 0:
                System.out.println("\n🎵 Retornando! 🎵\n");
                break;
            default:
                System.out.println("\n❌ Opção inválida!\n");
                break;
        }
    }

    public static void exibirEstatisticas() {
        System.out.println("-".repeat(20));
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        double duracaoTotal = 0;


        System.out.println("Total de músicas: " + musicas.size());

        for (int i = 0; i < musicas.size(); i++) {
            duracaoTotal += musicas.get(i).getDuracaoSegundos();
        }

        System.out.println("Duração total: " + formatarDuracao((int) (duracaoTotal)));
        System.out.println("Duração média: " + formatarDuracao((int) (duracaoTotal/musicas.size())));
     }

    public static String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }

}