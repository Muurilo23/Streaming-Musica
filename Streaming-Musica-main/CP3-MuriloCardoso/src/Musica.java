public class Musica {
    private String titulo;
    private String artista;
    private double duracaoSegundos;
    private String genero;
    private final String[] GENEROS_VALIDOS = {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};


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
        if (titulo.isEmpty()) {
            System.out.println("O nome não pode estar vazio.");
            return;
        }

        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista.isEmpty()) {
            System.out.println("O nome não pode estar vazio.");
            return;
        }
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
        for (String genero_valido : GENEROS_VALIDOS) {
            if (genero.equals(genero_valido)) {
                this.genero = genero;
                return;
            }
        }
        System.out.println("-".repeat(20));
        System.out.println("Não é um gêneroe valido.");
        return;
    }
}
