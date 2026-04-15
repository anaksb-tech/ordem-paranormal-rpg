public class EnpapX extends Inimigo {

    public EnpapX(String nome) {
        super(nome, 300, 35, "com um soco esmagador", "com um chute devastador");
    }

    @Override
    public void atacar(Jogador alvo) {
        if (Math.random() < 0.3) {
            System.out.println("O " + this.nome + " arrasta suas correntes amaldiçoadas e as arremessa contra " + alvo.getNome() + "!");
            alvo.receberDano(this.danoAtaque + 10);
        } else {
            super.atacar(alvo);
        }
    }
}