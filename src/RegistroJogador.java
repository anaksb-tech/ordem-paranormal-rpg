// Arquivo: RegistroJogador.java
// Propósito: Representa uma única entrada no ranking, com nome e pontuação.

import java.io.Serializable;

// implements Serializable: Permite que objetos desta classe sejam convertidos em bytes para serem salvos em um arquivo.
// implements Comparable<RegistroJogador>: Permite que uma lista de objetos desta classe seja ordenada.
public class RegistroJogador implements Serializable, Comparable<RegistroJogador> {

    private static final long serialVersionUID = 1L; // Controle de versão para a serialização
    private String nome;
    private int pontuacao;

    public RegistroJogador(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    // Este é o método que define como os registros serão ordenados.
    // Estamos dizendo para ordenar da maior pontuação para a menor.
    @Override
    public int compareTo(RegistroJogador outro) {
        return Integer.compare(outro.getPontuacao(), this.pontuacao);
    }

    @Override
    public String toString() {
        return "Jogador: " + nome + " - Pontuação: " + pontuacao;
    }
}
