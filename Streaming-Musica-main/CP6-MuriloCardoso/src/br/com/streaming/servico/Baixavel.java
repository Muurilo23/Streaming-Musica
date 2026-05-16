package br.com.streaming.servico;
import br.com.streaming.modelo.Musica;

public interface Baixavel {
    void baixarMusica(Musica musica);
    void removerDownload(Musica musica);
    boolean estaBaixada(Musica musica);
    int getTamanhoBaixados();
}
