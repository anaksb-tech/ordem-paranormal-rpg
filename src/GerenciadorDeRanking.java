// Arquivo: GerenciadorDeRanking.java
// Propósito: Gerencia a leitura e escrita do arquivo de ranking.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorDeRanking {

    private static final String NOME_ARQUIVO = "ranking.dat"; // Usamos .dat para indicar um arquivo de dados

    // Carrega os registros do arquivo
    @SuppressWarnings("unchecked") // Suprime o aviso de cast não verificado, pois confiamos na estrutura do arquivo
    public ArrayList<RegistroJogador> carregarRanking() {
        ArrayList<RegistroJogador> ranking = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            ranking = (ArrayList<RegistroJogador>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Normal se o arquivo ainda não existe. Retorna uma lista vazia.
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o ranking: " + e.getMessage());
        }
        return ranking;
    }

    // Salva um novo registro no arquivo
    public void salvarPontuacao(String nome, int pontuacao) {
        ArrayList<RegistroJogador> ranking = carregarRanking();
        ranking.add(new RegistroJogador(nome, pontuacao));
        Collections.sort(ranking); // Ordena a lista usando o método compareTo da classe RegistroJogador

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }

    // Exibe o ranking formatado no console
    public void exibirRanking() {
        ArrayList<RegistroJogador> ranking = carregarRanking();
        System.out.println("\n--- RANKING DOS MELHORES INVESTIGADORES ---");
        if (ranking.isEmpty()) {
            System.out.println("Nenhum recorde ainda. Seja o primeiro a vencer!");
        } else {
            for (int i = 0; i < ranking.size(); i++) {
                System.out.println((i + 1) + "º. " + ranking.get(i));
            }
        }
        System.out.println("-------------------------------------------");
    }
}
