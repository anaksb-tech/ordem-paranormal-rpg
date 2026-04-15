public class Ocultista extends Jogador {

    public Ocultista(String nome, String classe, int vida, int sanidade) {
        super(nome, "Ocultista", 80, 120);
    }
    @Override
    public void atacar(Inimigo alvo) {
        ritualDano(alvo);
    }
    public void usarRitual(String tipoRitual, Jogador alvoJogador, Inimigo alvoInimigo) {
        switch (tipoRitual.toLowerCase()) {
            case "dano":
                ritualDano(alvoInimigo);
                break;
            case "cura":
                ritualCura(alvoJogador);
                break;
            case "defesa":
                ritualDefesa();
                break;
            default:
                System.out.println(this.nome + " não conhece ou não pode usar o ritual '" + tipoRitual + "'.");
                break;
        }
    }
    private void ritualDano(Inimigo alvo) {
        int custoSanidade = 15;
        int danoRitual = 30;

        if (this.sanidade >= custoSanidade) {
            this.setSanidade(this.getSanidade() - custoSanidade);
            System.out.println(this.nome + " conjura o ritual 'Morte Súbita' contra " + alvo.getNome() + ", causando " + danoRitual + " de dano e consumindo " + custoSanidade + " de sanidade.");
            alvo.receberDano(danoRitual);
        } else {
            System.out.println(this.nome + " não tem sanidade suficiente para conjurar Morte Súbita! Sanidade necessária: " + custoSanidade + ".");

        }
    }
    private void ritualCura(Jogador alvo) {
        if (alvo.estaMorto()) {
            System.out.println(OrdemParanormalRPG.YELLOW + alvo.getNome() + " está derrotado e não pode ser curado." + OrdemParanormalRPG.RESET);
            return;
        }
        int custoSanidade = 10; // Custo de sanidade para o ritual de cura
        int curaRitual = 25;    // Quantidade de vida restaurada

        if (this.sanidade >= custoSanidade) {
            this.setSanidade(this.getSanidade() - custoSanidade);
            // Garante que a vida não exceda a vida máxima
            alvo.setVida(Math.min(alvo.getVidaMaxima(), alvo.getVida() + curaRitual));
            System.out.println(this.nome + " conjura o ritual 'Toque Curativo' em " + alvo.getNome() + ", restaurando " + curaRitual + " de vida e consumindo " + custoSanidade + " de sanidade.");
        } else {
            System.out.println(this.nome + " não tem sanidade suficiente para conjurar Toque Curativo! Sanidade necessária: " + custoSanidade + ".");
        }
    }
    private void ritualDefesa() {
        int custoSanidade = 12;

        if (this.sanidade >= custoSanidade) {
            this.setSanidade(this.getSanidade() - custoSanidade);
            System.out.println(this.nome + " se concentra, tecendo uma 'Barreira Paranormal'. O próximo dano será reduzido significativamente e consome " + custoSanidade + " de sanidade.");
            this.setDefendendo(true);
        } else {
            System.out.println(this.nome + " não tem sanidade suficiente para conjurar Barreira Paranormal! Sanidade necessária: " + custoSanidade + ".");
        }
    }

    @Override
    public void defender() {
        ritualDefesa();
    }
}