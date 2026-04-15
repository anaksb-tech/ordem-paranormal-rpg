public class Combatente extends Jogador {

    public Combatente(String nome, String classe, int vida, int sanidade) {
        super(nome, classe, 120, 80);
    }

    @Override
    public void atacar(Inimigo alvo) {
        int danoBase = 25;

        System.out.println(this.nome + " golpeia " + alvo.getNome() + " com seu robusto bastão de ferro, causando " + danoBase + " de dano!");
        alvo.receberDano(danoBase);
    }

    @Override
    public void defender() {
        System.out.println(this.nome + " se posiciona, usando seu corpo e o bastão de ferro para bloquear. O próximo dano que ele receberá será reduzido pela metade.");
        this.setDefendendo(true);
    }
}