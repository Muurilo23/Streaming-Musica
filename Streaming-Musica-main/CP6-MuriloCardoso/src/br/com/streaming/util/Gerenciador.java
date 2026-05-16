package br.com.streaming.util;

import br.com.streaming.modelo.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gerenciador {
    private static final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    public Usuario criarUsuario(Scanner scanner, List<Usuario> usuarios) {
        int planoContaUsuario;
        int opcao;
        int tipoContaUsuario = 0;

        while (true) {
            System.out.println("=== SISTEMA DE STREAMING ===");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Login");
            System.out.println("3. Listar usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção (0-3): ");
            opcao = scanner.nextInt();


            switch (opcao) {
                case 1:
                    scanner.nextLine();
                    String nomeUsuario = "";
                    String emailUsuario = "";

                    do {
                        System.out.print("Digite seu nome: ");
                        nomeUsuario = scanner.nextLine().trim().toUpperCase();

                        if (nomeUsuario.isBlank()) {
                            System.out.println("- ERRO: O campo \"Nome\" não deve estar vazio.");
                        }

                    } while (nomeUsuario.isBlank());

                    do {
                        System.out.print("Digite seu email: ");
                        emailUsuario = scanner.nextLine().trim().toUpperCase();

                        if (emailUsuario.isBlank()) {
                            System.out.println("- ERRO: O campo \"Email\" não deve estar vazio.");
                        }

                        if (this.emailJaExiste(usuarios, emailUsuario)) {
                            System.out.println("- ERRO: Esse Email já está vinculado a algum usuário. Tente novamente com outro email.");
                        } else {
                            break;
                        }

                    } while (true);


                    while (true) {
                        try {
                            System.out.println("=== ESCOLHA O TIPO DE CONTA ===");
                            System.out.println("1. Free (Gratuito)");
                            System.out.println("2. Premium (Pago)");
                            System.out.print("Escolha: ");

                            tipoContaUsuario = scanner.nextInt();
                            scanner.nextLine();

                            if (tipoContaUsuario > 3) {
                                throw new IllegalArgumentException("Selecione uma opção válida (1-2)");
                            }

                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("- ERRO: " + e.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("- ERRO: Somente números.");
                        }
                    }

                    if (tipoContaUsuario == 2) {
                        while (true) {
                            System.out.println("=== ESCOLHA O PLANO PREMIUM ===");
                            System.out.println("1. Mensal (R$ 19,90)");
                            System.out.println("2. Anual (R$ 199,00)");
                            System.out.println("3. Familiar (R$ 29,90)");
                            System.out.print("Escolha: ");

                            try {
                                planoContaUsuario = scanner.nextInt();

                                if (planoContaUsuario == 1) {
                                    UsuarioPremium usuario2 = new UsuarioPremium(nomeUsuario, emailUsuario, "MENSAL");
                                    System.out.println("✅ Conta Premium criada com sucesso!");
                                    scanner.nextLine();

                                    usuarios.add(usuario2);
                                    return usuario2;
                                } else if (planoContaUsuario == 2) {
                                    UsuarioPremium usuario2 = new UsuarioPremium(nomeUsuario, emailUsuario, "ANUAL");
                                    System.out.println("✅ Conta Premium criada com sucesso!");
                                    scanner.nextLine();

                                    usuarios.add(usuario2);
                                    return usuario2;
                                } else if (planoContaUsuario == 3) {
                                    UsuarioPremium usuario2 = new UsuarioPremium(nomeUsuario, emailUsuario, "FAMILIAR");
                                    System.out.println("✅ Conta Premium criada com sucesso!");
                                    scanner.nextLine();

                                    usuarios.add(usuario2);
                                    return usuario2;
                                } else {
                                    throw new IllegalArgumentException("Escolha uma opção válida (1-3).");
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("- ERRO: Somente números inteiros.");
                                scanner.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println("- ERRO: " + e.getMessage());
                            }
                        }

                    }

                    UsuarioFree usuario1 = new UsuarioFree(nomeUsuario, emailUsuario);
                    System.out.println("✅ Conta Free criada com sucesso!");
                    usuarios.add(usuario1);

                    return usuario1;
                case 2:
                    if (usuarios.isEmpty()) {
                        System.out.println("- ERRO: Não há usuários cadastrados.");
                        break;
                    }

                    int idUsuarios = 0;
                    int posicaoUsuarioLogin = 0;

                    System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
                    for (Usuario u : usuarios) {
                        idUsuarios++;
                        if (u instanceof UsuarioFree) {
                            System.out.println("ID: " + idUsuarios + " | NOME: " + u.getNome() + " | PLANO: GRATUITO" + " | EMAIL: " + u.getEmail());
                        } else if (u instanceof UsuarioPremium) {
                            UsuarioPremium u2 = (UsuarioPremium) u;
                            System.out.println("ID: " + idUsuarios + " | NOME: " + u2.getNome() + " | PLANO: PREMIUM " + u2.getPlano().toUpperCase() + " | EMAIL: " + u2.getEmail());
                        }

                    }

                    System.out.println("===============================\n");

                    while (true) {
                        try {
                            System.out.print("Informe o ID para realizar o login: ");
                            posicaoUsuarioLogin = scanner.nextInt();
                            scanner.nextLine();

                            return usuarios.get(posicaoUsuarioLogin-1);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("- ERRO: Por favor, inserir uma posição válida.");
                        } catch (InputMismatchException e) {
                            System.out.println("- ERRO: Somente números, nada de letras.");
                        }

                    }

                case 3:
                    if (usuarios.isEmpty()) {
                        System.out.println("- ERRO: Não há usuários cadastrados.");
                        break;
                    }

                    System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
                    for (Usuario u : usuarios) {
                        if (u instanceof UsuarioFree) {
                            System.out.println("NOME: " + u.getNome() + " | PLANO: GRATUITO" + " | EMAIL: " + u.getEmail());
                        } else if (u instanceof UsuarioPremium) {
                            UsuarioPremium u2 = (UsuarioPremium) u;
                            System.out.println("NOME: " + u2.getNome() +
                                    " | PLANO: PREMIUM " + u2.getPlano().toUpperCase() +
                                    " | EMAIL: " + u2.getEmail());
                        }
                    }
                    System.out.println("===============================\n");


                    break;
                case 0:
                    scanner.nextLine();
                    return null;
                default:
                    System.out.println("- ERRO: Selecione uma opção válida.");
                    scanner.nextLine();
            }

        }

    }

    public  boolean emailJaExiste(List<Usuario> usuarios, String email) {
        if (usuarios.isEmpty()) {
            return false;
        }
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void menuGeral() {
        System.out.println("-".repeat(20));
        System.out.println("=== SISTEMA DE STREAMING DE MÚSICA | MENU GERAL ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar Playlist & Conta");
        System.out.println("6. Exibir estatísticas");
        System.out.println("7. Voltar");
        System.out.println("0. Sair");
        System.out.println("-".repeat(20));
        System.out.print("Escolha uma opção: ");
    }

    public String formatarDuracao(int segundos) {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        return String.format("%d:%02d", minutos, segs);
    }

    public void listarMusicas(List<Musica> musicas) {
        if (musicas.isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("- ERRO: Não há músicas disponiveis.");
            return;
        }

        System.out.println("\nPOSIÇÃO | MÚSICAS | ARTISTA | GÊNERO | DURAÇÃO");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i+1) + " | "
                    + musicas.get(i).getTitulo() + " | "
                    + musicas.get(i).getArtista() + " | "
                    + musicas.get(i).getGenero() + " | "
                    + this.formatarDuracao((int) (musicas.get(i).getDuracaoSegundos())));
        }
        System.out.println();
    }

    public void baixarMusica(List<Musica> musicas, Usuario usuario, Scanner scanner) {
        if (musicas.isEmpty()) {
            System.out.println("- ERRO: Não há músicas para baixar.");
            return;
        }

        if (usuario instanceof UsuarioPremium) {
            UsuarioPremium usuarioPremium = (UsuarioPremium) usuario;

            for (int i = 0; i < musicas.size(); i++) {
                System.out.println("ID: " + (i+1) + " | NOME: " + musicas.get(i).getTitulo().toUpperCase() + " | ARTISTA: " + musicas.get(i).getArtista().toUpperCase());
            }

            while (true) {
                try {
                    System.out.print("- Informe a música pelo ID (Digite \"0\" para cancelar a operação): ");
                    int idMusica = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (idMusica == -1) {
                        System.out.println("\n=================");
                        System.out.println("🎵 Saindo....");
                        System.out.println("===================\n");
                        break;
                    }

                    Musica musica = musicas.get(idMusica);
                    usuarioPremium.baixarMusica(musica);
                    System.out.println("🎵 Música baixada com sucesso!");
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("- ERRO: Insira o ID válido.");
                } catch (InputMismatchException e) {
                    System.out.println("- ERRO: Somente números.");
                    scanner.nextLine();
                }
            }

        }
    }

    public void exibirDetalhesDeUmaPlaylist(Scanner scanner, Usuario usuario) {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("- ERRO: Não tem nenhum playlist disponível no momento.");
            return;
        }

        boolean encontrou = false;

        try {
            System.out.println("-".repeat(20));
            System.out.print("Informe uma parte ou o nome completo da playlist: ");
            String nomePlaylist = scanner.nextLine().trim();

            for (Playlist playlist : usuario.getPlaylists()) {
                if (playlist.getNome().toLowerCase().contains(nomePlaylist.toLowerCase())) {

                    encontrou = true;
                    System.out.println("\n---");
                    System.out.println("🎶 PLAYLIST: ");
                    System.out.println("NOME: " + playlist.getNome() + " | QUANT. MÚSICAS: "
                            + playlist.getQuantidadeMusicas() + " | DURAÇÃO TOTAL: "
                            + this.formatarDuracao((int) (playlist.getDuracaoTotal())));

                    System.out.println();

                    if (playlist.temMusica()) {
                        System.out.println("🎧 MÚSICAS DA PLAYLIST: ");
                        System.out.println("MÚSICA | ARTISTA | GÊNERO | DURAÇÃO");
                        for (Musica musica : playlist.getMusicas()) {
                            System.out.println(musica.getTitulo() + " | " + musica.getArtista() + " | " + musica.getGenero() + " | " + this.formatarDuracao((int) (musica.getDuracaoSegundos())));
                        }
                    }

                }

            }

            if (!encontrou) {
                System.out.println(" - ESSA PLAYLIST NÃO FOI ENCONTRADA - ");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Por favor, preencha o campo corretamente.");
        }
    }

    public void exibirEstatisticas(List<Usuario> usuarios) {
        System.out.println("-".repeat(20));
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        double duracaoTotal = 0;
        int usuariosFree = 0;
        int usuariosPremium = 0;
        int reproducaoTotal = 0;
        int reproducaoPremium = 0;
        int anunciosExibidos = 0;
        int reproducaoFree = 0;

        System.out.println("Total de usuários: " + usuarios.size());
        for (Usuario usuario1 : usuarios) {
            if (usuario1 instanceof UsuarioFree) {
                usuariosFree++;
                reproducaoTotal += usuario1.getHistoricoReproducao().size();
                reproducaoFree += usuario1.getHistoricoReproducao().size();
                anunciosExibidos += ((UsuarioFree) usuario1).getAnuncios();
            } else {
                usuariosPremium++;
                reproducaoTotal += usuario1.getHistoricoReproducao().size();
                reproducaoPremium += usuario1.getHistoricoReproducao().size();
            }
        }

        System.out.println("- Free: " + usuariosFree + " usuários");
        System.out.println("- Premium: " + usuariosPremium + " usuários");



        System.out.println("\nReproduções Totais: " + reproducaoTotal);
        System.out.printf("- Free: %d (%.2f%%)%n", reproducaoFree, ((double) reproducaoFree * 100.0) / reproducaoTotal);
        System.out.printf("- Premium: %d (%.2f%%)%n", reproducaoPremium, ((double) reproducaoPremium * 100.0) / reproducaoTotal);
        System.out.println("\nAnúncios exibidos: " + anunciosExibidos);

    }

    public void listarPlayList(Usuario usuario) {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há playlists disponiveis.");
            return;
        }

        usuario.listarPlaylist();
    }

    public void reproduzirMusica(Usuario usuario, Scanner scanner) {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("- ERRO: Esse usuário não possui playlist.");
            return;
        }

        boolean temMusica = false;

        for (int i = 0; i < usuario.getPlaylists().size(); i++) {
            Playlist playlist = usuario.getPlaylist(i);

            if (playlist.temMusica()) {
                System.out.println("=".repeat(20));
                System.out.println("🎵 - PLAYLIST: " + playlist.getNome());
                System.out.println("=".repeat(20));

                temMusica = true;

                for (int j = 0; j < playlist.getMusicas().size(); j++) {

                    usuario.reproduzirMusica(playlist.getMusicas().get(j));

                    System.out.println("=".repeat(20));
                    System.out.print("- CONTINUAR? [S/N]: ");
                    char continuar = scanner.next().toUpperCase().charAt(0);

                    if (continuar == 'N') {
                        System.out.println(" -  RETORNANDO - ");
                        return;
                    }
                }
            }

        }

        if (!temMusica) {
            System.out.println("- ERRO: A playlist do usuário está vazia.");
        }
    }

    public void assinarPremium(Scanner scanner, Usuario usuario, List<Usuario> usuarios) {
        System.out.println("-".repeat(20));
        System.out.println("Escolha o plano Premium:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");

        while (true) {
            try {
                System.out.print("Escolha: ");
                int planoContaUsuario = scanner.nextInt();
                scanner.nextLine();

                if (planoContaUsuario > 3) {
                    throw new IllegalArgumentException("Insira uma opção válida (1-3).");
                }

                while (true) {
                    try {

                        if (planoContaUsuario == 1) {
                            System.out.println("✅ Conta atualizada pro Premium criada com sucesso!");
                            usuario = new UsuarioPremium(usuario, "Mensal");

                            usuario = new UsuarioPremium(usuario, "Mensal");
                            usuarios.set(usuarios.indexOf(usuario), usuario);
                            break;
                        } else if (planoContaUsuario == 2) {
                            System.out.println("✅ Conta atualizada pro Premium criada com sucesso!");
                            usuario = new UsuarioPremium(usuario, "Anual");

                            usuario = new UsuarioPremium(usuario, "Mensal");
                            usuarios.set(usuarios.indexOf(usuario), usuario);
                            break;
                        } else if (planoContaUsuario == 3) {
                            System.out.println("✅ Conta atualizada pro Premium criada com sucesso!");
                            usuario = new UsuarioPremium(usuario, "Familiar");

                            usuario = new UsuarioPremium(usuario, "Mensal");
                            usuarios.set(usuarios.indexOf(usuario), usuario);
                            break;
                        } else {
                            throw new IllegalArgumentException("Escolha uma opção válida (1-3).");
                        }
                    }  catch (InputMismatchException e) {
                        System.out.println("- ERRO: Somente números inteiros.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println("- ERRO: " + e.getMessage());
                    }

                }

                System.out.println("\n==========================");
                System.out.println("🎵 Agora você é um Usuário Premium!");
                System.out.println("==========================\n");

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("- ERRO: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("- ERRO: Somente números, nada de letras.");
            }
        }
    }

    public static void cadastrarMusica(Scanner scanner, List<Musica> musicas) {
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

            if (duracao <= 0 || duracao > 3600) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Duração inválida! A duração não pode ultrpassar 3600 segundo e não pode ter menos de 0 segundos.");
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

    public static void buscarMusicaPorNome(List<Musica> musicas, Scanner scanner) {
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

    public void criarPlaylist(List<Musica> musicas, Usuario usuario, Scanner scanner) {
        if (musicas.isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("❌ Opção inválida: Não há músicas disponíveis.");
            return;
        }

        int escolhaPlaylist;
        String nomePlaylist;

        while (true) {
            try {
                System.out.println("=".repeat(20));
                System.out.println("1. PlayList Personalizada.");
                System.out.println("2. PlayList Automática.");
                System.out.print("- Sua escolha: ");
                escolhaPlaylist = scanner.nextInt();
                scanner.nextLine();

                if (escolhaPlaylist < 1 || escolhaPlaylist > 2) {
                    throw new IllegalArgumentException("- ERRO: Escolha uma opção válida.");
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("- ERRO: Somente números, nada de letras.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("=".repeat(20));

        if (escolhaPlaylist == 1) {
            System.out.print("- Informe o nome da playlist: ");
            nomePlaylist = scanner.nextLine();

            if (usuario instanceof UsuarioPremium) {
                ((UsuarioPremium) usuario).criarPlaylist(nomePlaylist);
            } else if (usuario instanceof UsuarioFree) {
                ((UsuarioFree) usuario).criarPlaylist(nomePlaylist);
            }

            System.out.println(" - PLAYLIST CRIADA - ");

        } else if (escolhaPlaylist == 2) {
            int playlistAutomatica;

            while (true) {
                try {
                    System.out.println("=== PLAYLISTS AUTOMÁTICAS ===");
                    System.out.println("1. Top 03 mais tocadas...");
                    System.out.println("2. Recomendadas para você...");
                    System.out.println("3. Adicionadas recentemente...");
                    System.out.print("Sua escolha: ");
                    playlistAutomatica = scanner.nextInt();
                    scanner.nextLine();

                    if (playlistAutomatica < 1 || playlistAutomatica > 3) {
                        throw new IllegalArgumentException("- ERRO: Informe uma opção válida (1 a 3).");
                    }

                    break;
                } catch (InputMismatchException e) {
                    System.out.println("- ERRO: Somente números, nada de letras.");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("- Informe o nome da playlist: ");
            nomePlaylist = scanner.nextLine();

            PlaylistAutomatica novaPlaylist = new PlaylistAutomatica(nomePlaylist);
            String regra = "";

            if (playlistAutomatica == 1) regra = "tops";
            else if (playlistAutomatica == 2) regra = "recomendadas";
            else if (playlistAutomatica == 3) regra = "recentes";

            novaPlaylist.setMusicas(novaPlaylist.fazerPlaylist(musicas, regra));

            if (novaPlaylist.getMusicas() == null) {
                return;
            }

            usuario.adicionarPlaylist(novaPlaylist);
            System.out.println("✅ Playlist Automática criada com sucesso!");
        }
    }

    public void adicionarMusica(Usuario usuario, Scanner scanner, List<Musica> musicas) {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("-".repeat(20));
            System.out.println("- ERRO: Você não criou nenhuma playlist até o momento.");
            return;
        }
        this.listarPlayList(usuario);
        int numPlaylist;
        int numMusica;

        try {
            System.out.println("Informe a posição da Playlist: ");
            numPlaylist = scanner.nextInt();
            Playlist playlist = usuario.getPlaylist(numPlaylist-1);
            scanner.nextLine();

            this.listarMusicas(musicas);
            System.out.print("Informe a posição da Música: ");
            numMusica = scanner.nextInt();
            scanner.nextLine();

            Musica musica = musicas.get(numMusica-1);
            playlist.adicionarMusica(musica);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ Por favor, preencha o campo corretamente.");
        }

    }

}
