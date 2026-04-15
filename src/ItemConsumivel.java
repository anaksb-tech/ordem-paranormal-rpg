public class ItemConsumivel extends Item{

    private int recuperaVida;
    private int recuperaSanidade;

    public ItemConsumivel(String nome, String descricao, int recuperaVida, int recuperaSanidade){

        super(nome, descricao);
        this.recuperaVida = recuperaVida;
        this.recuperaSanidade = recuperaSanidade;

    }

    public void usar (Jogador jogador) {
        System.out.println(jogador.getNome() + " usa " + this.nome + " ." + this.descricao);

        int vidaAtual = jogador.getVida();
        int vidaMaxima = jogador.getVidaMaxima();
        jogador.setVida(Math.min(vidaMaxima, vidaAtual + this.recuperaVida));


        int sanidadeAtual = jogador.getSanidade();
        int sanidadeMaxima = jogador.getSanidadeMaxima();
        jogador.setSanidade(Math.min(sanidadeMaxima, sanidadeAtual + this.recuperaSanidade));

        System.out.println("Status atual: Vida -> " + jogador.getVida() + "| Sanidade -> " + jogador.getSanidade());
    }

    public int getRecuperaVida() {
        return recuperaVida;
    }

    public int getRecuperaSanidade() {
        return recuperaSanidade;
    }
}