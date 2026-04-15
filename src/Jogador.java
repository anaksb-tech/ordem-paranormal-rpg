public abstract class Jogador {

    protected String nome;
    protected String classe;
    protected int vida;
    protected int sanidade;
    protected int vidaMaxima;
    protected int sanidadeMaxima;
    protected boolean defendendo;
    protected Inventario inventario;
    protected int vidasExtras = 0;
    protected boolean estaMorto;

    public Jogador(String nome, String classe, int vida, int sanidade) {
        this.nome = nome;
        this.classe = classe;
        this.vidaMaxima = vida;
        this.sanidadeMaxima = sanidade;
        this.vida = vida;
        this.sanidade = sanidade;
        this.defendendo = false;
        this.inventario = new Inventario();
        this.estaMorto = false;
    }

    public abstract void atacar(Inimigo alvo);
    public abstract void defender();

    public void ganharVidaExtra() {
        if (estaMorto) {
            System.out.println(OrdemParanormalRPG.YELLOW + this.nome + " está morto e não pode mais ganhar vidas extras." + OrdemParanormalRPG.RESET);
            return;
        }
        this.vidasExtras++;
        System.out.println(OrdemParanormalRPG.YELLOW + this.nome + " ganhou uma vida extra! Você sente uma nova força vital fluir." + OrdemParanormalRPG.RESET);
    }

    public void receberDano(int dano){
        if (estaMorto) {
            return;
        }
        if (this.defendendo){
            dano /= 2;
            System.out.println(this.nome + " se defende. Dano reduzido para " + dano + ".");
            this.defendendo = false;
        }

        this.vida -= dano;
        System.out.println(this.nome + " recebeu " + dano + " de dano.");

        if (this.vida <= 0){
            if (this.vidasExtras > 0) {
                this.vidasExtras--;
                this.vida = this.vidaMaxima / 2;
                System.out.println(OrdemParanormalRPG.GREEN + this.nome + " sofreu um golpe fatal, mas usou uma vida extra para voltar ao combate!" + OrdemParanormalRPG.RESET);
            } else {
                this.vida = 0;
                System.out.println(OrdemParanormalRPG.RED + this.nome + " não tem mais forças para continuar..." + OrdemParanormalRPG.RESET);
            }
        }
        System.out.println("Vida atual: " + this.vida);
    }

    public boolean estaVivo(){
        return vida > 0 && !estaMorto;
    }

    public void exibirStatus(){
        System.out.println("Status de " + this.nome + " | " + this.classe + ": Vida = " + this.vida + "/" + this.vidaMaxima + ", Sanidade = " + this.sanidade + "/" + this.sanidadeMaxima + ", Vidas Extras: " + this.vidasExtras);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getSanidade() { return sanidade; }
    public void setSanidade(int sanidade) { this.sanidade = sanidade; }
    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }
    public int getSanidadeMaxima() { return sanidadeMaxima; }
    public boolean isDefendendo() { return defendendo; }
    public void setDefendendo(boolean defendendo) { this.defendendo = defendendo; }
    public Inventario getInventario() { return inventario; }
    public void setInventario(Inventario inventario) { this.inventario = inventario; }

    public boolean estaMorto() {
        return estaMorto;
    }
}
