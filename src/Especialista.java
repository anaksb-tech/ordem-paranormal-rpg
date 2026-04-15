public class Especialista extends Jogador {

    private boolean estaFurtivo; //
    private int custoSanidade = 15;

    public Especialista(String nome, String classe) {
        super(nome, classe, 90, 100);
        this.estaFurtivo = false;
    }

    @Override
    public void atacar(Inimigo alvo) {
        int danoBase = 15;
        System.out.println(this.nome + " dispara com precisão contra " + alvo.getNome() + ", causando " + danoBase + " de dano.");
        alvo.receberDano(danoBase);
    }

    @Override
    public void defender() {
        System.out.println(this.nome + " se move furtivamente, tentando evitar o ataque do inimigo.");
        this.setDefendendo(true);
    }
    public boolean ativarFurtividade() {
        if (this.getSanidade() >= custoSanidade) {
            this.setSanidade(this.getSanidade() - custoSanidade);
            this.estaFurtivo = true;
            System.out.println(OrdemParanormalRPG.CYAN + getNome() + " se move furtivamente, atraindo a atenção dos inimigos para sua posição! (" + custoSanidade + " de sanidade gasta)" + OrdemParanormalRPG.RESET);
            return true;
        } else {
            System.out.println(OrdemParanormalRPG.RED + getNome() + " não tem sanidade suficiente para usar Furtividade. Sanidade necessária: " + custoSanidade + "." + OrdemParanormalRPG.RESET);
            return false;
        }
    }
    public void desativarFurtividade() {
        this.estaFurtivo = false;
    }
    public boolean estaFurtivo() {
        return estaFurtivo;
    }
}