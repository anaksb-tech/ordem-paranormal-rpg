public class ZumbiDeSangue extends Inimigo {

    public ZumbiDeSangue(String nome) {
        super(nome, 50, 15, "com suas garras afiadas", "com sua mordida sangrenta");
    }

    @Override
    public void atacar(Jogador alvo) {
       System.out.println("O " + this.nome + " avança sedento por sangue!");
        super.atacar(alvo);
    }
}