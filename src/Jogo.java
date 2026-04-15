public interface Jogo {

    void iniciarJogo();
    void jogarTurno(Jogador jogador);
    boolean jogoAcabou();

    static int criarNumeroAleatorio(int min, int max){
        return (int) ((Math.random() * (max - min + 1)) + min);
    }

}
