public class Musica {
    private String titulo;
    private String artista;
    private double duracaoSegundos;
    private String genero;

    public Musica(String titulo, String artista, double duracaoSegundos, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.genero = genero;
    }

    public void exibir() {
        System.out.println("--------------------");
        System.out.println("NOME | ARTISTA | DURAÇÃO SEG. | GENERO");
        System.out.println(this.titulo + " | " + this.artista + " | " + this.duracaoSegundos + " | " + this.genero);
    }

    public boolean contemTitulo(String buscar) {
        if (this.titulo.toLowerCase().equals(buscar.toLowerCase())) {
            return true;
        }

        return false;

    }

    public boolean contemArtista(String buscar) {
        if (this.artista.toLowerCase().equals(buscar.toLowerCase())) {
            return true;
        }
        return false;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public double getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(double duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
