import java.util.ArrayList;

public class Lugar {
    // Atributos
    String nome;
    String descricao;
    ArrayList<Item> itensEscondidos;
    ArrayList<String> pistas;
    ArrayList<Inimigo> inimigos;
    private boolean visitado; // NOVO ATRIBUTO

    // Construtor
    public Lugar(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.itensEscondidos = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.inimigos = new ArrayList<>();
        this.visitado = false; // Todo lugar começa como não visitado
    }

    // Métodos para popular o lugar
    public void adicionarInimigo(Inimigo inimigo) {
        this.inimigos.add(inimigo);
    }

    public void adicionarItem(Item item) {
        this.itensEscondidos.add(item);
    }

    public void adicionarPista(String pista) {
        this.pistas.add(pista);
    }

    // Verifica se ainda existem inimigos vivos no local
    public boolean temInimigosVivos() {
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    // Exibe a descrição do lugar
    public void descreverLugar() {
        System.out.println("\nVocê está em: " + this.nome);
        System.out.println(this.descricao);
    }

    // Getters e Setters para o novo atributo
    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    // Getters
    public String getNome() { return nome; }
    public ArrayList<Inimigo> getInimigos() { return inimigos; }
    public ArrayList<Item> getItensEscondidos() { return itensEscondidos; }
    public ArrayList<String> getPistas() { return pistas; }
}