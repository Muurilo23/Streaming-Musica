import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Playlist criarPlaylist(String nome) {
        if (nome.isEmpty()) {
            System.out.println("Nome inválido.");
            return null;
        }

        Playlist playlist = new Playlist(nome);
        return playlist;
    }


    public void listarPlaylist() {
        if (this.playlists.isEmpty()) {
            System.out.println("--------------------");
            System.out.println("Está vazia.");
            return;
        }

        System.out.println();
        System.out.println("POSIÇÃO | PLAYLIST ");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i+1) + " | " + playlists.get(i).getNome());
        }

    }

    public void adicionarPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    // Getters Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.isEmpty()) {
            System.out.println("O nome não pode ser vazio.");
            return;
        }
        this.nome = nome;
    }

    public Playlist getPlaylist(int indice) {
        return this.playlists.get(indice);
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        if (playlists.isEmpty()) {
            System.out.println("A playlist está vazia.");
            return;
        }

        this.playlists = playlists;
    }
}
