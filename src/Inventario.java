import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {

    private HashMap<String, Item> itens;

    public Inventario() {
        this.itens = new HashMap<>();
    }

    public void adicionarItem(Item item){
        this.itens.put(item.getNome(), item);
        System.out.println(item.getNome() + " foi adicionado ao inventário.");
    }

    public void removerItem(String nomeItem){
        this.itens.remove(nomeItem);
    }

    public Item obterItem(String nomeItem){
        return this.itens.get(nomeItem);
    }

    public void listarItens(){
        System.out.println("===== INVENTÁRIO =====");
        if(itens.isEmpty()){
            System.out.println(" O inventário está vazio.");
        }else {
            for (Item item : itens.values()){

                System.out.println("- " + item.getNome() + ": " + item.getDescricao());

            }
        }
        System.out.println("=====================");
    }
    public ArrayList<ItemConsumivel> getItensConsumiveis() {
        ArrayList<ItemConsumivel> consumiveis = new ArrayList<>();
        for (Item item : itens.values()) {
            if (item instanceof ItemConsumivel) {
                consumiveis.add((ItemConsumivel) item);
            }
        }
        return consumiveis;
    }
    public HashMap<String, Item> getItens() {
        return itens;
    }
}
