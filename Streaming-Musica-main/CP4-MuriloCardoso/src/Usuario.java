import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists = new ArrayList<>();
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public Usuario(String nome, String email, ArrayList<Playlist> playlists, ArrayList<Musica> historicoReproducao) {
        this.nome = nome;
        this.email = email;
        this.playlists = playlists;
        this.historicoReproducao = historicoReproducao;
    }

    public void criarPlaylist(String nome) {
        if (nome.isEmpty()) {
            System.out.println("- ERRO: Nome inválido.");
            return;
        }

        Playlist playlist = new Playlist(nome);
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        if (historicoReproducao.isEmpty()) {
            System.out.println("- Você não ouviu nenhuma música.");
        }

        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        for (Musica m : historicoReproducao) {
            m.exibir();
        }
    }

    public void listarPlaylist() {
        if (this.playlists.isEmpty()) {
            System.out.println("--------------------");
            System.out.println("- ERRO: Está vazia.");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public ArrayList<Musica> getHistoricoReproducao() {
        return historicoReproducao;
    }

    public void setHistoricoReproducao(ArrayList<Musica> historicoReproducao) {
        this.historicoReproducao = historicoReproducao;
    }
}
