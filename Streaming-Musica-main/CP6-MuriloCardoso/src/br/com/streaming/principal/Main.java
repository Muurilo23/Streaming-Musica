package br.com.streaming.principal;
import br.com.streaming.modelo.*;
import br.com.streaming.util.Gerenciador;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Gerenciador gerenciador = new Gerenciador();
    static Usuario usuario;

    public static void main(String[] args) {
        usuario = gerenciador.criarUsuario(scanner, usuarios);

        if (usuario == null) {
            System.out.println("\n=== ENCERRANDO... ===");
        } else {
            int opcao;
            do {
                gerenciador.menuGeral();
                opcao = scanner.nextInt();
                scanner.nextLine();
                processarEscolhaGeral(opcao);
            } while (opcao != 0);
        }
    }

    public static void menuFree() {
        System.out.println("-".repeat(20));
        System.out.println("=== SISTEMA DE STREAMING DE MÚSICA | MENU FREE ===");
        System.out.println("1. Reproduzir Música");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (máx. 3)");
        System.out.println("4. 💎 Fazer upgrade para Premium");
        System.out.println("5. Adicionar música a uma playlist");
        System.out.println("0. Sair");
        System.out.print("\n- Sua escolha: ");
        int escolhaFree = scanner.nextInt();
        scanner.nextLine();
        processarDadosFree(escolhaFree);
    }

    public static void processarEscolhaGeral(int opcao) {
        switch (opcao) {
            case 1:
                gerenciador.cadastrarMusica(scanner, musicas);
                break;
            case 2:
                gerenciador.listarMusicas(musicas);
                break;
            case 3:
                gerenciador.buscarMusicaPorNome(musicas, scanner);
                break;
            case 4:
                gerenciador.criarPlaylist(musicas, usuario, scanner);
                break;
            case 5:
                if (usuario instanceof UsuarioFree) {
                    menuFree();
                } else if (usuario instanceof UsuarioPremium) {
                    menuPremium();
                }
                break;
            case 6:
                gerenciador.exibirDetalhesDeUmaPlaylist(scanner, usuario);
                break;
            case 7:
                usuario = gerenciador.criarUsuario(scanner, usuarios);

                if (usuario == null) {
                    opcao = 0;
                }

                break;
            case 0:
                System.out.println("\n🎵 Obrigado por usar o Sistema de Streaming! Até logo! 🎵\n");
                break;
            default:
                System.out.println("❌ Opção inválida");
                break;
        }
    }

    public static void processarDadosFree(int escolha) {
        UsuarioFree usuarioFree = (UsuarioFree) usuario;

        switch (escolha) {
            case 1:
                gerenciador.reproduzirMusica(usuario, scanner);
                break;
            case 2:
                usuarioFree.exibirHistorico();
                break;
            case 3:
                gerenciador.criarPlaylist(musicas, usuario, scanner);
                break;
            case 4:
                gerenciador.assinarPremium(scanner, usuario, usuarios);
                break;
            case 5:
                gerenciador.adicionarMusica(usuario, scanner, musicas);
                break;
            case 0:
                return;
            default:
                System.out.println("- ERRO: Escolha inválida.");
        }

    }

    public static void menuPremium() {
        System.out.println("-".repeat(20));
        System.out.println("=== SISTEMA DE STREAMING DE MÚSICA | MENU PREMIUM ===");
        System.out.println("1. Reproduzir música (Alta Qualidade)");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (ilimitado)");
        System.out.println("4. Baixar música");
        System.out.println("5. Ver músicas baixadas");
        System.out.println("6. Adicionar música a uma playlist");
        System.out.println("0. Sair");
        System.out.print("\n- Sua escolha: ");
        int escolhaPremium = scanner.nextInt();
        scanner.nextLine();
        processarDadosPremium(escolhaPremium);
    }

    public static void processarDadosPremium(int escolha) {
        UsuarioPremium usuarioPremium = (UsuarioPremium) usuario;

        switch (escolha) {
            case 1:
                gerenciador.reproduzirMusica(usuario, scanner);
                break;
            case 2:
                usuarioPremium.exibirHistorico();
                break;
            case 3:
                gerenciador.criarPlaylist(musicas, usuario, scanner);
                break;
            case 4:
                gerenciador.baixarMusica(musicas, usuario, scanner);
                break;
            case 5:
                usuarioPremium.listarMusicasBaixadas();
                break;
            case 6:
                gerenciador.adicionarMusica(usuario, scanner, musicas);
                break;
            case 0:
                return;
            default:
                System.out.println("- ERRO: Escolha inválida.");
        }

    }

}