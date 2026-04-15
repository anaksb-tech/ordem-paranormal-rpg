public class ZumbiDeSangueBestial extends Inimigo {

    public ZumbiDeSangueBestial(String nome) {
        super(nome, 70, 25, "com suas garras de sangue", "com sua mordida de sangue");
    }
    @Override
    public void atacar(Jogador alvo) {
        if (alvo.isDefendendo()) { // Usamos isDefendendo() como proxy para "esconder"
            System.out.println("O " + this.nome + " fareja o ar, sentindo a presença de " + alvo.getNome() + " e começa a procurar...");
            // Gasta a rodada procurando e não ataca.
            alvo.setDefendendo(false); // Revela o jogador após procurar
        } else {
            // Comportamento de ataque padrão da classe Inimigo
            super.atacar(alvo);
        }
    }
}