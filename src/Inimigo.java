public class Inimigo {
    // Atributos comuns a todos os inimigos
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int danoAtaque;
    protected String tipoAtaque1; // Descrição do primeiro tipo de ataque
    protected String tipoAtaque2; // Descrição do segundo tipo de ataque

    public Inimigo(String nome, int vida, int danoAtaque, String tipoAtaque1, String tipoAtaque2) {
        this.nome = nome;
        this.vidaMaxima = vida;
        this.vida = vida;
        this.danoAtaque = danoAtaque;
        this.tipoAtaque1 = tipoAtaque1;
        this.tipoAtaque2 = tipoAtaque2;
    }

    public void atacar(Jogador alvo) {
        String ataqueDescricao = (Math.random() < 0.5) ? tipoAtaque1 : tipoAtaque2;
        System.out.println("O " + this.nome + " ataca " + alvo.getNome() + " " + ataqueDescricao + "!");
        alvo.receberDano(this.danoAtaque);
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
        System.out.println("O " + this.nome + " recebeu " + dano + " de dano. Vida atual: " + this.vida);
        if (this.vida == 0) {
            System.out.println("O " + this.nome + " foi derrotado!");
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void exibirStatus() {
        System.out.println(
                "Status do Monstro " + this.nome + ": " +
                        "Vida=" + this.vida + "/" + this.vidaMaxima +
                        ", Ataques: " + tipoAtaque1 + " ou " + tipoAtaque2
        );
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getDanoAtaque() {
        return this.danoAtaque;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }
}