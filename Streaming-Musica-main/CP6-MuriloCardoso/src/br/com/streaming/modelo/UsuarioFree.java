package br.com.streaming.modelo;

public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    private int anuncios = 0;

    public UsuarioFree(String nome, String email) {
        super(nome, email); // Chama construtor da superclasse
        this.contadorReproducoes = 0;
    }

    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            System.out.println("💎 Assine Premium para playlists ilimitadas!");
            return;
        }

        PlaylistPersonalizada playlist = new PlaylistPersonalizada(nome);
        playlists.add(playlist);
        System.out.println("✅ br.com.streaming.modelo.Playlist criada!");
    }

    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
        anuncios++;
    }

    public int getContadorReproducoes() {
        return contadorReproducoes;
    }

    public int getAnuncios() {
        return anuncios;
    }

    public void setContadorReproducoes(int contadorReproducoes) {
        this.contadorReproducoes = contadorReproducoes;
    }

    public void setAnuncios(int anuncios) {
        this.anuncios = anuncios;
    }
}
