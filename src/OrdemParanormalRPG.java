import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrdemParanormalRPG implements Jogo {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    private ArrayList<Jogador> jogadores;
    private ArrayList<Lugar> mapa;
    private int indiceLocalAtual;
    private boolean fimDeJogo;
    private Scanner scanner;

    public OrdemParanormalRPG() {
        this.jogadores = new ArrayList<>();
        this.mapa = new ArrayList<>();
        this.indiceLocalAtual = 0;
        this.fimDeJogo = false;
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        iniciarJogo();

        int turnoAtual = 0;
        while (!fimDeJogo) {
            Jogador jogadorDaVez = jogadores.get(turnoAtual % jogadores.size());
            if (jogadorDaVez.estaVivo()) {
                jogarTurno(jogadorDaVez);
                fimDeJogo = jogoAcabou();
            }
            turnoAtual++;
        }

        pontuacao();
        scanner.close();
    }

    @Override
    public void iniciarJogo() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\u001B[36m📖 Uma nova investigação começa... Você acaba de entrar em Ordem Paranormal!\u001B[0m");
        System.out.println("------------------------------------------------------------------------------");

        System.out.print("\n🔎 Pressione ENTER para prosseguir...");
        scanner.nextLine();

        System.out.println(YELLOW + BOLD + "📜 Missão Iniciada:" + RESET);
        System.out.println( "\nVocês são agentes oficiais da Ordo Realitas, cujo objetivo principal é proteger civis e eliminar monstros paranormais. 🕵️‍♂️👻" );
        System.out.println( "Desta vez, vocês foram designados a um caso em uma pequena cidade chamada Três Rios, onde diversos cidadãos foram mortos por uma criatura estranha. ☠️" );
        System.out.println("Os poucos que conseguiram fugir a descreveram como grande e vermelha, mas não deram muitos detalhes. Eles pareciam assustados demais para falar...");
        System.out.println("ou, talvez, tivessem algo a esconder. 🫢🔍" );

        System.out.print("\n🔎 Pressione ENTER para prosseguir...");
        scanner.nextLine();

        System.out.println(PURPLE + "\nA missão de vocês é ir até a cidade, encontrar o monstro que matou grande parte dos cidadãos e descobrir como ele surgiu." + RESET);
        System.out.println(YELLOW + "\nBoa sorte, jogadores! 🍀" + RESET);

        System.out.print("\n🔎 Pressione ENTER para prosseguir...");
        scanner.nextLine();

        System.out.println(GREEN + "\nDentro deste jogo, existem três personagens com classes distintas que possuem formas diferentes de lutar. Escolha com sabedoria! ⚔️🛡️🔮" + RESET);

        System.out.println(PURPLE +"\nAgatha (Ocultista) 🔮"+ RESET);

        System.out.println(CYAN + "Agatha é uma jovem adulta misteriosa e inteligente. Sempre se interessou por eventos paranormais e, por se intrometer em um deles, \n" +
                "acabou chamando a atenção da Ordo Realitas, que a convocou para se tornar uma agente. Para ela, o Outro Lado é misterioso, perigoso e, de certa forma, cativante.\n" +
                "Agatha faz parte do grupo de agentes que visa compreender e dominar os mistérios do paranormal para usá-los no combate contra o próprio Outro Lado. Possui a habilidade\n" +
                "de usar e aprender rituais por meio de itens amaldiçoados.\n"+ RESET);

        System.out.println(PURPLE +"\nJoe (Combatente) ⚔️"+ RESET);

        System.out.println(CYAN +"Um ex-militar carismático e com muitos amigos, Joe foi recrutado para a Ordo Realitas por um velho companheiro de guerra. Mesmo tendo um \n" +
                "filho pequeno e uma esposa para cuidar, ele aceitou o convite a fim de protegê-los desse mal. Treinado para lutar com todo tipo de armamento, Joe tem a força e a coragem \n" +
                "necessárias para encarar os perigos de frente. É o tipo de agente que prefere abordagens diretas, costumando atirar primeiro e perguntar depois. Embora tenha grande habilidade \n" +
                "com armas de fogo, sua arma de confiança para combate próximo é um robusto bastão de ferro.\n"+ RESET);

        System.out.println(PURPLE +"Arthur (Especialista) 🧠"+ RESET);

        System.out.println(CYAN +"Arthur sempre foi um estudioso apaixonado por mistérios. Certo dia, durante uma investigação pessoal, ele conseguiu invadir o sistema da Ordo \n" +
                "Realitas, onde ficam os registros de criaturas paranormais. Impressionados com sua capacidade e cérebro brilhante, em vez de puni-lo, a Ordem o convocou. Arthur é um agente que \n" +
                "confia mais na esperteza do que na força bruta. Como Especialista, ele se vale de conhecimento técnico, raciocínio rápido e lábia para resolver mistérios e enfrentar o paranormal. \n" +
                "Ele tem a habilidade de se mover furtivamente para evitar confrontos diretos, atrasando a ação do inimigo ao forçá-lo a procurá-lo, uma tática ideal para quem, como ele, prefere usar \n" +
                "armas de fogo de longa distância.\n"+ RESET);

        System.out.print("\n🔎 Pressione ENTER para prosseguir...");
        scanner.nextLine();

        System.out.println("\nVocês chegam à pequena e aparentemente, sempre tranquila cidade de Três Rios. Desta vez, porém, uma neblina densa e pesada paira sobre o local, limitando a visibilidade \n" +
                "a meros dez passos. Como investigadores paranormais, vocês sabem que essa névoa não é natural, ela é o indício inconfundível de que algo sombrio e muito pesado aconteceu por aqui. Essa é \n" +
                "uma clara indicação de que algo terrível ocorreu e que possivelmente, há monstros à solta. É hora de seguir com a investigação.\n");

        System.out.print("\n🔎 Pressione ENTER para prosseguir...");
        scanner.nextLine();



        criarMundo();

        ArrayList<Jogador> personagensDisponiveis = new ArrayList<>();
        personagensDisponiveis.add(new Combatente("Joe", "Combatente", 0, 0)); // Ajuste vida/sanidade inicial se necessário
        personagensDisponiveis.add(new Ocultista("Agatha", "Ocultista", 0, 0)); // Ajuste vida/sanidade inicial se necessário
        personagensDisponiveis.add(new Especialista("Arthur", "Especialista" )); // Ajuste vida/sanidade inicial se necessário

        for (int i = 1; i <= 2; i++) {
            System.out.println(GREEN +"\n--- Jogador " + i + ", escolha seu personagem: ---" + RESET);
            for (int j = 0; j < personagensDisponiveis.size(); j++) {
                Jogador p = personagensDisponiveis.get(j);
                System.out.println((j + 1) + ". " + p.getNome() + " (" + p.getClasse() + ")");
            }

            int escolha = lerEntradaNumerica(1, personagensDisponiveis.size());
            scanner.nextLine();

            Jogador personagemEscolhido = personagensDisponiveis.remove(escolha - 1);
            this.jogadores.add(personagemEscolhido);
            System.out.println("Jogador " + i + " escolheu: " + personagemEscolhido.getNome());
        }
    }

    @Override
    public void jogarTurno(Jogador jogador) {
        System.out.println("\n---------------------------------------------");
        System.out.println(GREEN + "--- Turno de " + jogador.getNome() + " (" + jogador.getClasse() + ") ---" + RESET);
        if (jogador instanceof Especialista) {
            ((Especialista) jogador).desativarFurtividade();
        }
        jogador.exibirStatus();

        Lugar localAtual = mapa.get(indiceLocalAtual);

        if (!localAtual.isVisitado()) {
            localAtual.descreverLugar();
            localAtual.setVisitado(true);
        } else {
            System.out.println("\nVocê está em: " + localAtual.getNome());
        }

        if (localAtual.temInimigosVivos()) {
            gerenciarCombate(jogador, localAtual);

            if (!localAtual.temInimigosVivos()) {
                System.out.println(YELLOW + "\nCom o último inimigo derrotado, o silêncio retorna ao local." + RESET);

                boolean eraZumbi = localAtual.getInimigos().stream().anyMatch(i -> i instanceof ZumbiDeSangue || i instanceof ZumbiDeSangueBestial);
                if (eraZumbi) {
                    gerenciarEscolhaDoCoracao();
                }

                if (!localAtual.getPistas().isEmpty()) {
                    String pistaEncontrada = localAtual.getPistas().remove(0);

                    if (localAtual.getNome().equals("Casa Abandonada")) {
                        System.out.println("Vocês conseguiram! Mesmo cansados, vocês veem um papel sobre a mesa ao lado da cama. Parece ter sido escrito às pressas: ");
                        System.out.println(CYAN + "\nPISTA ENCONTRADA: \"" + pistaEncontrada + "\"" + RESET);
                        System.out.print(BOLD + CYAN + "Isso parece um sinal claro de qual lugar será o próximo.. " + RESET);
                        GerenciadorDeAudio.tocarSom("AudioCasa.wav");

                        try {

                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("Erro ao esperar pelo áudio da Casa Abandonada: " + e.getMessage());
                        }
                        System.out.println("\nAo Hospital!");
                    }
                    else if (localAtual.getNome().equals("Hospital")) {
                        System.out.println("Parabéns, jogadores! Vocês conseguiram derrotar o zumbi de sangue! Ao final da luta, mesmo cansados, vocês se atentam ao redor e percebem uma carta sobre a mesa de recepção.");
                        System.out.println(CYAN + "\nPISTA ENCONTRADA: \"" + pistaEncontrada + "\"" + RESET);
                        System.out.print(BOLD + CYAN + " Após lê-la, sentem-se ainda mais próximos de desvendar esse mistério, mas a jornada não acaba aqui." + RESET);
                        GerenciadorDeAudio.tocarSom("AudioHospital.wav");

                        try {
                            Thread.sleep(28000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("Erro ao esperar pelo áudio do bilhete do Hospital: " + e.getMessage());
                        }
                        System.out.println("\nPróxima parada: Delegacia!");
                    }
                }
            }

        } else {
            gerenciarInvestigacao(jogador, localAtual);
        }
        fimDeJogo = jogoAcabou();
    }

    private void gerenciarCombate(Jogador jogador, Lugar local) {

        System.out.println("Inimigos se aproximam! Prepare-se para o combate!");
        for (Inimigo inimigo : local.getInimigos()) {
            if (inimigo.estaVivo()) inimigo.exibirStatus();
        }

        System.out.println("\nO que você faz?");
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Usar Item");
        if (jogador instanceof Especialista) {
            System.out.println("4. Furtividade (Gasta Sanidade, faz inimigos procurarem por você)");
        }

        int maxEscolha = (jogador instanceof Especialista) ? 4 : 3;
        int escolhaAcao = lerEntradaNumerica(1, maxEscolha);
        scanner.nextLine();

        if (escolhaAcao == 1 && jogador instanceof Ocultista) {
            gerenciarTurnoOcultista((Ocultista) jogador, local);
        } else {
            switch (escolhaAcao) {
                case 1:
                    ArrayList<Inimigo> alvosVivos = local.getInimigos().stream().filter(Inimigo::estaVivo).collect(Collectors.toCollection(ArrayList::new));
                    if (alvosVivos.isEmpty()) {
                        System.out.println("Não há alvos vivos para atacar.");
                        return;
                    }
                    System.out.println("Escolha o alvo:");
                    for (int i = 0; i < alvosVivos.size(); i++) {
                        System.out.println((i+1) + ". " + alvosVivos.get(i).getNome());
                    }
                    int escolhaAlvo = lerEntradaNumerica(1, alvosVivos.size());
                    scanner.nextLine();
                    jogador.atacar(alvosVivos.get(escolhaAlvo - 1));
                    break;
                case 2:
                    jogador.defender();
                    break;
                case 3:
                    Inventario inventarioDoJogador = jogador.getInventario();
                    ArrayList<ItemConsumivel> itensConsumiveis = inventarioDoJogador.getItensConsumiveis(); // Usa o novo método!

                    if (itensConsumiveis.isEmpty()) {
                        System.out.println("Seu inventário está vazio ou não possui itens consumíveis para usar.");
                        break;
                    }
                    System.out.println("\nItens consumíveis disponíveis:");
                    for (int i = 0; i < itensConsumiveis.size(); i++) {
                        ItemConsumivel consumivel = itensConsumiveis.get(i);
                        System.out.println((i + 1) + ". " + consumivel.getNome() +
                                " (Vida: +" + consumivel.getRecuperaVida() +
                                ", Sanidade: +" + consumivel.getRecuperaSanidade() +
                                ") - " + consumivel.getDescricao());
                    }
                    System.out.println("0. Cancelar");

                    int escolhaItem = lerEntradaNumerica(0, itensConsumiveis.size());
                    scanner.nextLine();
                    if (escolhaItem == 0) {
                        System.out.println("Você decide não usar nenhum item.");
                        break;
                    }
                    ItemConsumivel itemEscolhido = itensConsumiveis.get(escolhaItem - 1);
                    itemEscolhido.usar(jogador);
                    inventarioDoJogador.removerItem(itemEscolhido.getNome());
                    System.out.println(GREEN + itemEscolhido.getNome() + " usado com sucesso!" + RESET);
                    break;
                case 4:
                    if (jogador instanceof Especialista) {
                        Especialista especialista = (Especialista) jogador;
                        if (!especialista.ativarFurtividade()) {
                            System.out.println("Ação de furtividade falhou. Ação perdida.");
                        }
                    } else {
                        System.out.println("Opção inválida para sua classe.");
                    }
                    break;
            }
        }
        if (!jogoAcabou() && local.temInimigosVivos()) {
            for (Inimigo inimigo : local.getInimigos()) {
                if (inimigo.estaVivo()) {
                    ArrayList<Jogador> jogadoresVivos = new ArrayList<>();
                    Especialista especialistaFurtivoNoMomento = null;

                    for (Jogador j : jogadores) {
                        if (j.estaVivo()) {
                            jogadoresVivos.add(j);
                            if (j instanceof Especialista && ((Especialista) j).estaFurtivo()) {
                                especialistaFurtivoNoMomento = (Especialista) j;
                            }
                        }
                    }
                    if (especialistaFurtivoNoMomento != null) {
                        System.out.println(RED + inimigo.getNome() + " parece distraído procurando " + especialistaFurtivoNoMomento.getNome() + " e perde o turno procurando-o!" + RESET);
                    } else if (!jogadoresVivos.isEmpty()) {
                        int alvoAleatorio = Jogo.criarNumeroAleatorio(0, jogadoresVivos.size() - 1);
                        inimigo.atacar(jogadoresVivos.get(alvoAleatorio));
                    } else {
                        System.out.println("Não há alvos disponíveis para " + inimigo.getNome() + " atacar neste momento.");
                    }
                }
            }
        }
    }

    private void gerenciarEscolhaDoCoracao() {
        System.out.println(PURPLE + "\nAo derrotar a criatura, seu coração paranormal pulsa no chão, oferecendo uma escolha..." + RESET);
        System.out.println("O coração pode conceder uma vida extra, mas apenas um pode pegá-lo.");

        Jogador jogador1 = jogadores.get(0);
        Jogador jogador2 = jogadores.get(1);

        boolean j1Vivo = jogador1.estaVivo();
        boolean j2Vivo = jogador2.estaVivo();

        if (j1Vivo && j2Vivo) {
            System.out.println("Vocês devem escolher simultaneamente: Ceder o coração ao seu parceiro ou Pegar para si mesmo.");
            System.out.println(RED + "A confiança é uma arma de dois gumes." + RESET);

            System.out.println("\n--- " + jogador1.getNome() + ", qual é a sua escolha? ---");
            System.out.println("1. Ceder o coração");
            System.out.println("2. Pegar o coração");
            int escolha1 = lerEntradaNumerica(1, 2);
            scanner.nextLine();

            System.out.println("\n--- " + jogador2.getNome() + ", qual é a sua escolha? ---");
            System.out.println("1. Ceder o coração");
            System.out.println("2. Pegar o coração");
            int escolha2 = lerEntradaNumerica(1, 2);
            scanner.nextLine();

            boolean jogador1Cedeu = (escolha1 == 1);
            boolean jogador2Cedeu = (escolha2 == 1);

            System.out.println("\n--- O Resultado da Escolha ---");
            if (jogador1Cedeu && !jogador2Cedeu) {
                System.out.println(jogador1.getNome() + " confia em seu parceiro, mas " + jogador2.getNome() + " age por instinto de sobrevivência.");
                System.out.println(jogador2.getNome() + " absorve o poder do coração!");
                jogador2.ganharVidaExtra();
            } else if (!jogador1Cedeu && jogador2Cedeu) {
                System.out.println(jogador2.getNome() + " confia em seu parceiro, mas " + jogador1.getNome() + " age por instinto de sobrevivência.");
                System.out.println(jogador1.getNome() + " absorve o poder do coração!");
                jogador1.ganharVidaExtra();
            } else if (jogador1Cedeu && jogador2Cedeu) {
                System.out.println("Em um raro ato de altruísmo mútuo, ambos cedem o coração. A confiança de vocês é forte, mas o poder se dissipa no ar, não reclamado por ninguém.");
            } else {
                System.out.println("A ganância fala mais alto e ambos tentam pegar o coração ao mesmo tempo. A energia instável explode e o poder é perdido para sempre.");
            }
        }
        else if (j1Vivo) {
            System.out.println(RED + jogador2.getNome() + " não resistiu aos ferimentos e não completou sua missão.Afinal, esse era o mais importante, certo?" + RESET);
            System.out.println(PURPLE + "\n" + jogador1.getNome() + " sente o peso da perda do seu parceiro. Não há escolha a ser feita." + RESET);
            System.out.println(jogador1.getNome() + " absorve o poder do coração, mas o gosto da vitória é amargo sem " + jogador2.getNome() + " ao seu lado.");
            jogador1.ganharVidaExtra();
        } else if (j2Vivo) {
            System.out.println(RED + jogador1.getNome() + " não resistiu aos ferimentos e não completou sua missão. Afinal, esse era o mais importante, certo?" + RESET);
            System.out.println(PURPLE + "\n" + jogador2.getNome() + " sente o peso da perda do seu parceiro. Não há escolha a ser feita." + RESET);
            System.out.println(jogador2.getNome() + " absorve o poder do coração, mas o gosto da vitória é amargo sem " + jogador1.getNome() + " ao seu lado.");
            jogador2.ganharVidaExtra();
        }
    }

    private void gerenciarTurnoOcultista(Ocultista agatha, Lugar local) {
        System.out.println("\n" + agatha.getNome() + ", qual ritual você deseja usar?");
        System.out.println("1. Dano (Morte Súbita - Custo 15 Sanidade)");
        System.out.println("2. Cura (Toque Curativo - Custo 10 Sanidade)");
        System.out.println("3. Defesa (Barreira Paranormal - Custo 12 Sanidade)");
        System.out.println("4. Cancelar");

        int escolhaRitual = lerEntradaNumerica(1, 4);
        scanner.nextLine();

        switch (escolhaRitual) {
            case 1:
                ArrayList<Inimigo> alvosVivos = local.getInimigos().stream().filter(Inimigo::estaVivo).collect(Collectors.toCollection(ArrayList::new));
                if (alvosVivos.isEmpty()) {
                    System.out.println("Não há alvos vivos para atacar.");
                    return;
                }
                System.out.println("Escolha o alvo do ritual:");
                for (int i = 0; i < alvosVivos.size(); i++) {
                    System.out.println((i+1) + ". " + alvosVivos.get(i).getNome());
                }
                int escolhaAlvo = lerEntradaNumerica(1, alvosVivos.size());
                scanner.nextLine();
                agatha.usarRitual("dano", null, alvosVivos.get(escolhaAlvo - 1));
                break;
            case 2:
                ArrayList<Jogador> jogadoresVivosParaCura = new ArrayList<>();
                for (Jogador j : jogadores) {
                    if (j.estaVivo()) {
                        jogadoresVivosParaCura.add(j);
                    }
                }
                if (jogadoresVivosParaCura.isEmpty()) {
                    System.out.println(RED + "Não há jogadores vivos para curar!" + RESET);
                    return;
                }
                System.out.println("Curar quem?");
                for(int i = 0; i < jogadoresVivosParaCura.size(); i++){
                    Jogador alvoPotencial = jogadoresVivosParaCura.get(i);
                    System.out.println((i+1) + ". " + alvoPotencial.getNome() + " (Vida: " + alvoPotencial.getVida() + "/" + alvoPotencial.getVidaMaxima() + ")");
                }

                int escolhaCura = lerEntradaNumerica(1, jogadoresVivosParaCura.size());
                scanner.nextLine();
                agatha.usarRitual("cura", jogadoresVivosParaCura.get(escolhaCura - 1), null);
                break;
            case 3:
                agatha.usarRitual("defesa", agatha, null);
                break;
            case 4:
                System.out.println(agatha.getNome() + " hesita e não faz nada...");
                break;
        }
    }

    private void gerenciarInvestigacao(Jogador jogador, Lugar local) {
        System.out.println("\nO local parece seguro por enquanto. O que você faz?");
        System.out.println("1. Procurar Itens");
        System.out.println("2. Mover para o próximo local");

        int escolha = lerEntradaNumerica(1, 2);
        scanner.nextLine();

        switch (escolha) {
            case 1:
                if (!local.getItensEscondidos().isEmpty()) {
                    Item itemEncontrado = local.getItensEscondidos().remove(0);
                    jogador.getInventario().adicionarItem(itemEncontrado);
                    System.out.println(GREEN + "Item encontrado: " + itemEncontrado.getNome() + RESET);
                } else {
                    System.out.println("Você não encontra mais nada de útil aqui.");
                }
                break;
            case 2:
                if (indiceLocalAtual < mapa.size() - 1) {
                    indiceLocalAtual++;
                    System.out.println("Vocês avançam para o próximo local...");
                } else {
                    System.out.println("Não há mais para onde ir a partir daqui.");
                }
                break;
        }
    }

    @Override
    public boolean jogoAcabou() {
        boolean todosMortos = jogadores.stream().noneMatch(Jogador::estaVivo);
        if (todosMortos) {
            System.out.println(RED + "Todos os investigadores pereceram... A escuridão venceu." + RESET);
            return true;
        }
        Lugar ultimoLugar = mapa.get(mapa.size() - 1);
        if (indiceLocalAtual == mapa.size() - 1 && !ultimoLugar.temInimigosVivos()) {
            System.out.println(YELLOW + "A entidade final foi derrotada! O caso foi resolvido... por enquanto." + RESET);
            return true;
        }
        return false;
    }

    public void pontuacao() {
        System.out.println(YELLOW + "\n--- Vocês falharam em deter o monstro. Ele avançou sobre outras vilas, exterminando todos os habitantes. \n" +
                "--- A Ordo Realitas sente a perda de agentes tão competentes, mas a dor pelas vidas inocentes perdidas é ainda maior. Fim de Jogo." + RESET);
        boolean vitoria = (indiceLocalAtual == mapa.size() - 1 && !mapa.get(mapa.size() - 1).temInimigosVivos());

        if (vitoria) {
            System.out.println("Missão cumprida! Vocês não só acabaram com o Enpap-X, como também salvaram diversos inocentes. A Ordo Realitas está \n" +
                    "muito feliz por ter agentes tão capazes em sua equipe. Ainda que a tragédia tenha transformado Leonardo no Enpap-X, o esquema\n" +
                    " político que ele tentava expor foi totalmente desfeito graças a vocês. Parabéns, vocês venceram!");
        } else {
            System.out.println("A missão falhou. O paranormal consumiu tudo.");
        }
    }

    private void criarMundo() {
        Lugar casaAbandonada = new Lugar("Casa Abandonada", "Pisar na primeira casa da cidade é adentrar o caos. A sala, a cozinha e a mesa de jantar estão em total desordem, \n" +
                "com grandes arranhões cobertos de sangue nas paredes e cadeiras que claramente foram arremessadas. Vocês ainda estão atrás de pistas, o medo não pode atrasar vocês. Vocês adentram \n" +
                "mais o local e chegam a um quarto com uma janela virada diretamente para a vila. Uma enorme bagunça indica que quem estava ali saiu às pressas. É então que vocês veem: uma criatura \n" +
                "grotesca com dentes tortos e uma pele vermelha que parece carne viva está no ambiente. É hora de lutar.");

        Lugar Hospital = new Lugar("Hospital", "Um sentimento angustiante e a neblina densa colaboram para o nervosismo e o medo, mas vocês precisam continuar, agentes! O cheiro \n" +
                "de hospital parece muito pior agora. Frascos de medicamentos estão derramados pelo chão e corpos humanos dilacerados pelo chão lhes dão arrepios, mas não é a primeira vez que veem algo \n" +
                "assim.Vocês adentram o lugar à procura de algum item que possa ajudar depois da luta na casa, ou de alguma pista para onde ir em seguida. É então que, finalmente, avistam uma criatura de \n" +
                "sangue bestial. Ela parece muito pior do que a anterior; sua estrutura agora imita um monstro quadrúpede, e seus dentes tortos revelam uma língua grande e nojenta. Essa não parece ser tão \n" +
                "fácil de deter quanto a outra.");

        Lugar Delegacia = new Lugar("Delegacia", "Dentro da delegacia, tudo está de cabeça para baixo: papéis espalhados por todo lado e a porta de ferro das celas está arrebentada e entreaberta. \n" +
                "Vocês decidem investigar. Ao abrirem a porta rapidamente, avistam uma criatura de sangue bestial. Ela parece muito pior do que às anteriores; sua estrutura agora imita um monstro quadrúpede, \n" +
                "e seus dentes tortos revelam uma língua grande e nojenta. Essa não parece ser tão fácil de deter quanto as outras. Boa batalha! ");

        Lugar galpaoFinal = new Lugar("Galpão Abandonado", "Na porta do galpão, vocês sentem que não há mais volta. É matar ou morrer, o Enpap-X deve ser detido para que não haja mais vítimas. \n" +
                "Com a verdade em suas mãos, uma pequena esperança surge mas desmorona no instante em que as portas se abrem. Revelando uma monstruosidade enorme de quatro braços, com cinco metros de altura e \n" +
                "largura gigantesca, a criatura busca vingança contra qualquer um em seu caminho, carregando o preço de uma eternidade de dor, sofrimento e tortura. Vocês precisam acabar com ela antes que ela \n" +
                "tenha tempo de acabar com vocês. Boa batalha!");

        casaAbandonada.adicionarPista("Não sei o que está acontecendo. De repente, acordei com diversos gritos e quando olhei pela janela, vi toda a vila correndo desesperada! Tenho que encontrar meu avô no Hospital \n" +
                "da cidade para levá-lo daqui o mais rápido possível.");
        casaAbandonada.adicionarInimigo(new ZumbiDeSangue("Zumbi de Sangue Lento"));

        Hospital.adicionarInimigo(new ZumbiDeSangue("Zumbi de Sangue Lento 1"));
        Hospital.adicionarInimigo(new ZumbiDeSangue("Zumbi de Sangue Lento 2"));
        Hospital.adicionarPista("Os policiais da cidade estão aqui de novo, pedindo materiais médicos como gases, tesouras e coisas do tipo. É estranho, já que quase não há operações por aqui, a cidade é bem tranquila! \n" +
                "Além disso, pessoas de preto do governo vêm frequentemente conversar com eles e depois saem para algum lugar. Queria ir à delegacia para ver que tipos de documentos existem lá. Tudo isso é muito estranho.");
        Hospital.adicionarItem(new ItemConsumivel("Biscoito", "Uma barra de cereal meio velha, mas comestível.", 0, 20));
        Hospital.adicionarItem(new ItemConsumivel("Medicamento", "Algumas pilulas encontradas no Hospital.", 25, 0));


        Delegacia.adicionarInimigo(new ZumbiDeSangueBestial("Zumbi de Sangue Bestial"));
        Delegacia.adicionarPista("—------------------------------\n" +
                "Ficha do Experimento Enpap-X\n" +
                "—------------------------------\n\n" +
                "Nome do objeto de estudo: Leonardo Ferreira\n\n" +
                "Função: Professor Universitário da Unirio\n\n" +
                "Local do Experimento: Três Rios, RJ.\n\n" +
                "Contexto: O experimento Enpap-X foi iniciado pois o alvo se intrometeu excessivamente em questões governamentais, quase desmantelando importantes esquemas políticos. \n" +
                "Foi necessário silenciá-lo, e por coincidência, um estudo químico e paranormal do governo estava em busca de novos sujeitos de teste. \n\n" +
                "Os testes devem ser realizados na pequena cidade de Três Rios, em um galpão abandonado afastado, para garantir o sigilo. \n" +
                "Façam-no durar, extraiam o máximo de informações e contatem-nos com os resultados.\n");


        galpaoFinal.adicionarInimigo(new EnpapX("Enpap-X, o Amaldiçoado"));

        this.mapa.add(casaAbandonada);
        this.mapa.add(Hospital);
        this.mapa.add(Delegacia);
        this.mapa.add(galpaoFinal);
    }

    private int lerEntradaNumerica(int min, int max) {
        int escolha = -1;
        while (escolha < min || escolha > max) {
            try {
                System.out.print("Sua escolha: ");
                escolha = scanner.nextInt();
                if (escolha < min || escolha > max) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            }
        }
        return escolha;
    }
}