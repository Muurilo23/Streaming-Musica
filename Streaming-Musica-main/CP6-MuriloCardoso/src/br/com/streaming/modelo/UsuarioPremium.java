package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;

import java.util.ArrayList;

public class UsuarioPremium extends Usuario implements Baixavel {
    private String plano; // Mensal, Anual, Familiar
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    public UsuarioPremium(Usuario usuarioAntigo, String plano) {
        super(usuarioAntigo.getNome(), usuarioAntigo.getEmail(), usuarioAntigo.getPlaylists(), usuarioAntigo.getHistoricoReproducao());
        this.plano = plano;
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica);
        musica.aumentarContadorMusica();
    }

    @Override
    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️ Música já está baixada!");
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        musicasBaixadas.remove(musica);
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        if (playlists.isEmpty()) {
            return false;
        }

        for (int i = 0; i < playlists.size(); i++) {

            for (int j = 0; j < playlists.get(i).getMusicas().size(); i++) {

                if (playlists.get(i).getMusicas().get(j) == musica) {
                    return true;
                }

            }
        }

        return false;
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }

        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public ArrayList<Musica> getMusicasBaixadas() {
        return musicasBaixadas;
    }

    public void setMusicasBaixadas(ArrayList<Musica> musicasBaixadas) {
        this.musicasBaixadas = musicasBaixadas;
    }
}
