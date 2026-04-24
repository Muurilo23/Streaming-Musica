import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome.toUpperCase();
    }

    public boolean temMusica() {
        if (musicas.isEmpty()) {
            return false;
        }
        return true;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
        System.out.println("--- MUSICA ADICIONADA ---");
    }

    public void removerMusica(Musica musica) {
        musicas.remove(musica);
        System.out.println("--- MUSICA REMOVIDA ---");
    }

    public void listarMusicas() {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("POSIÇÃO | NOME | ARTISTA");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i+1) + " | " + musicas.get(i).getTitulo() + " | " + musicas.get(i).getArtista());
        }
    }

    public double getDuracaoTotal() {
        if (musicas.isEmpty()) {
            return 0;
        }

        double total = 0;
        for (Musica musica : musicas) {
            total += musica.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return musicas.size();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        if (musicas.isEmpty()) {
            System.out.println("A lista está vazia, não há como colocar ela.");
            return;
        }

        this.musicas = musicas;
    }
}
