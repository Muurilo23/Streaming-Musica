package br.com.streaming.modelo;

import java.util.*;

public class PlaylistAutomatica extends Playlist {
    private String criterio; // "top", "recomendadas", "recentes"

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    public PlaylistAutomatica(String nome) {
        super(nome);
    }

    public List<Musica> fazerPlaylist(List<Musica> musicas, String criterio) {

        if (musicas.size() < 3) {
            System.out.println("- ERRO: Não há músicas o suficientes cadastradas.");
            return null;
        }

        if (criterio.toLowerCase().equals("tops")) {
            List<Musica> topDez = new ArrayList<>();
            Collections.sort(musicas, Comparator.comparingInt(Musica::getTocadas).reversed());

            System.out.println("\n=== TOP TRÊS MÚSICAS ===");
            for (int i = 0; i < 3; i++) {
                System.out.println("POSIÇÃO: " + (i+1) +
                        "° | MÚSICA: " + musicas.get(i).getTitulo() +
                        " | VEZES TOCADAS " + musicas.get(i).getTocadas() +
                        " | ARTISTA: " + musicas.get(i).getArtista());

                topDez.add(musicas.get(i));
            }

            System.out.println("=== MÚSICAS ADICIONADAS ===");
            System.out.println("=======================\n");
            return topDez;
        } else if (criterio.toLowerCase().equals("recomendadas")) {

            List<Musica> recomendadasPlaylist = new ArrayList<>();
            Collections.sort(musicas, Comparator.comparingInt(Musica::getTotalPlaylist).reversed());

            System.out.println("\n=== MÚSICAS RECOMENDADAS ===");
            for (int i = 0; i < 3; i++) {
                System.out.println("POSIÇÃO: " + (i+1) +
                        "° | MÚSICA: " + musicas.get(i).getTitulo() +
                        " | POPULARIDADE EM PLAYLIST " + musicas.get(i).getTotalPlaylist() +
                        " | ARTISTA: " + musicas.get(i).getArtista());
                recomendadasPlaylist.add(musicas.get(i));
            }
            System.out.println("=== MÚSICAS ADICIONADAS ===");
            System.out.println("=======================\n");
            return recomendadasPlaylist;
        } else if (criterio.equals("recentes")) {
            List<Musica> recentes = new ArrayList<>();
            Collections.reverse(musicas);

            for (int i = 0; i < 3; i++) {
                System.out.println("POSIÇÃO: " + (i+1) +
                        "° | MÚSICA: " + musicas.get(i).getTitulo() +
                        " | ARTISTA: " + musicas.get(i).getArtista());
                recentes.add(musicas.get(i));
            }
            System.out.println("=== MÚSICAS ADICIONADAS ===");
            System.out.println("=======================\n");
            return recentes;
        }

        return null;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 br.com.streaming.modelo.Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();

        if (criterio.equals("top")) {
            // Adicionar músicas mais tocadas
        } else if (criterio.equals("recomendadas")) {
            // Adicionar músicas recomendadas
        }
    }






}