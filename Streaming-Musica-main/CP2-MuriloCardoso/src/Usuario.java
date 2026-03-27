import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();


    public void criarPlaylist(String nome) {
        Playlist playlist = new Playlist(nome);
        System.out.println("✅ Playlist Criada");
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
        this.nome = nome;
    }

    public Playlist getPlaylist(int indice) {
        return this.playlists.get(indice);
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
