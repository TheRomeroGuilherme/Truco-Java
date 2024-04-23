
//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo Mesa1.java

import java.util.Scanner;

public class Mesa1 {

    public static void iniciarJogo1() {
        Scanner nome1x1 = new Scanner(System.in);

        // Pedir o nome dos jogadores
        System.out.println("Digite o nome do jogador 1:");
        String nomeJogador1 = nome1x1.nextLine();

        System.out.println("Digite o nome do jogador 2:");
        String nomeJogador2 = nome1x1.nextLine();

        // Chamar o método iniciarJogo1x1 com os nomes dos jogadores
        iniciarJogo1x1(nomeJogador1, nomeJogador2);
        nome1x1.close();
    }

    public static void iniciarJogo1x1(String nomeJogador1, String nomeJogador2) {
        Scanner nomex1 = new Scanner(System.in);
        int valorPartidaJogador1 = 0;
        int valorPartidaJogador2 = 0;
        int wi = 1;
        while (wi != 0) {
            int pontosJogador1 = 0;
            int pontosJogador2 = 0;
            int escolhaJogador1 = 0;
            int escolhaJogador2 = 0;

            // Criação do baralho e embaralhamento
            Baralho baralho = new Baralho();
            baralho.embaralhar();

            // Distribuição de cartas para os jogadores
            Jogador jogador1 = new Jogador(nomeJogador1);
            Jogador jogador2 = new Jogador(nomeJogador2);
            for (int i = 1; i <= 3; i++) {
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }

            // Virar uma carta na mesa para definir a manilha
            Carta cartaVirada = baralho.virarCarta();
            String valorManilha = cartaVirada.getValor();
            String naipeManilha = cartaVirada.getNaipe();
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("Carta virada na mesa (manilha): " + cartaVirada);

            // Determinar o valor e naipe da manilha seguinte
            String valorManilhaSeguinte = Baralho.proximoValor(valorManilha);
            String naipeManilhaSeguinte = Baralho.proximoNaipe(naipeManilha);
            System.out.println("Próxima manilha: " + valorManilhaSeguinte + " de " + naipeManilhaSeguinte);

            // Laço para a contagem de rodadas
            for (int rodada = 1; rodada <= 3; rodada++) {
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|\t\t\tRodada: " + rodada + "\t\t\t\t|");
                System.out.println("+---------------------------------------------------------------+");

                // Jogada do jogador1
                System.out.println(
                        nomeJogador1
                                + "\nescolha uma carta(digite 0, 1 ou 2) ou digite -1 para trucar:");
                exibirCartas(jogador1);
                escolhaJogador1 = nomex1.nextInt();
                if (escolhaJogador1 == -1) {
                    pedirTruco1(nomeJogador1, nomeJogador2, jogador1, jogador2, pontosJogador1, pontosJogador2, rodada);
                }
                Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                System.out.println("+---------------------------------------------------------------+");

                // Jogada do jogador2
                System.out.println(
                        nomeJogador2
                                + "\nescolha uma carta(digite 0, 1 ou 2) ou digite -1 para trucar:");
                exibirCartas(jogador2);
                escolhaJogador2 = nomex1.nextInt();
                if (escolhaJogador2 == -1) {
                    pedirTruco2(nomeJogador1, nomeJogador2, jogador1, jogador2, pontosJogador1, pontosJogador2, rodada);
                }
                Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                System.out.println("+---------------------------------------------------------------+\n\n");

                // Determina o vencedor da rodada pelo ID da carta
                if (cartaJogadaJogador1.getID() > cartaJogadaJogador2.getID()) {
                    pontosJogador1++;
                    System.out.println("\n\n+---------------------------------------------------------------+");
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("Vencedor da rodada: " + nomeJogador1);
                } else if (cartaJogadaJogador1.getID() < cartaJogadaJogador2.getID()) {
                    pontosJogador2++;
                    System.out.println("\n\n+---------------------------------------------------------------+");
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("Vencedor da rodada: " + nomeJogador2);
                } else {
                    System.out.println("Empate na rodada " + rodada);
                }

                // Exibe o placar após cada rodada
                contabilizarPontos(pontosJogador1, pontosJogador2, valorPartidaJogador1, valorPartidaJogador2,
                        nomeJogador1, nomeJogador2, rodada);
            }
        }
        nomex1.close();
    }

    public static void exibirCartas(Jogador jogador) {
        System.out.println("Cartas de " + jogador.getNome() + ":");
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println(i + ": " + jogador.getMao().get(i));
        }
    }

    public static void contabilizarPontos(int pontosJogador1, int pontosJogador2, int valorPartidaJogador1,
            int valorPartidaJogador2, String nomeJogador1, String nomeJogador2, int rodada) {

        // Verifica quem ganhou a rodada
        int vitoriasJogador1 = 0;
        int vitoriasJogador2 = 0;
        boolean jogador1Venceu = pontosJogador1 > pontosJogador2;
        boolean jogador2Venceu = pontosJogador2 > pontosJogador1;

        // Atualiza o placar da partida apenas para o vencedor da rodada
        if (jogador1Venceu) {
            valorPartidaJogador1++;
            vitoriasJogador1++;
        } else if (jogador2Venceu) {
            valorPartidaJogador2++;
            vitoriasJogador2++;
        }

        // Exibe o placar da rodada
        System.out.println("\n\n+---------------------------------------------------------------+");
        System.out.println(
                "Placar rodada: " + nomeJogador1 + " " + pontosJogador1 + " x " + pontosJogador2 + " " + nomeJogador2);
        System.out.println("+---------------------------------------------------------------+");

        // Verifica se um dos jogadores alcançou 12 pontos na partida
        boolean jogador1Chegou12 = valorPartidaJogador1 >= 12;
        boolean jogador2Chegou12 = valorPartidaJogador2 >= 12;

        if (jogador1Chegou12 || jogador2Chegou12) {
            System.out.println("Jogo encerrado!");

            if (jogador1Chegou12 && jogador2Chegou12) {
                System.out.println("Empate!");
            } else if (jogador1Chegou12) {
                System.out.println("Vencedor: " + nomeJogador1);
            } else {
                System.out.println("Vencedor: " + nomeJogador2);
            }

            exibirPlacarFinal(nomeJogador1, valorPartidaJogador1, vitoriasJogador1, nomeJogador2,
                    valorPartidaJogador2, vitoriasJogador2, rodada);
        }

    }

    public static void devolverCartaAoBaralho(Baralho baralho, Carta carta) {
        baralho.adicionarCarta(carta); // Adiciona a carta de volta ao baralho
    }

    private static void exibirPlacarFinal(String nomeJogador1, int valorPartidaJogador1, int vitoriasJogador1,
            String nomeJogador2, int valorPartidaJogador2, int vitoriasJogador2, int rodada) {
        System.out.println("\n\n\n+---------------------------------------------------------------+");
        System.out.println("Placar Final: " + nomeJogador1 + " " + valorPartidaJogador1 + " x " + valorPartidaJogador2
                + " " + nomeJogador2);
        System.out.println(nomeJogador1 + " Rodadas Vencidas: " + vitoriasJogador1);
        System.out.println(nomeJogador2 + " Rodadas Vencidas: " + vitoriasJogador2);
        System.out.println("+---------------------------------------------------------------+\n\n\n");
    }

    public static void pedirTruco1(String nomeJogador1, String nomeJogador2, Jogador jogador1, Jogador jogador2,
            int pontosJogador1, int pontosJogador2,
            int rodada) {
        int escolhaJogador1 = 0;
        int escolhaJogador2 = 0;
        Scanner nomex1 = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println(
                "|  " + jogador1.getNome() + ", você pediu truco: escolha quantos pontos essa rodada vai ter |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("Digite '3', '6', '9' ou '12'");
        int truco = nomex1.nextInt();
        System.out.println("Atenção !!!");
        System.out.println(jogador1.getNome() + " Pediu truco valendo " + truco + " pontos nessa rodada!");
        System.out.println("Opções:");
        System.out.println("1 = Aceitar");
        System.out.println("2 = Correr");
        int respostaJogador2 = nomex1.nextInt();

        if (escolhaJogador1 == -1) {
            truco = 0;
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|  Você pediu truco: escolha quantos pontos essa rodada vai ter |");
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("Digite '3' , '6' , '9'  ou '12'");
            truco = nomex1.nextInt();
            switch (truco) {
                case 3:
                    System.out.println("Atenção !!!");
                    System.out.println(jogador1 + " Pediu truco valendo" + truco + " Pontos nessa rodada!");
                    System.out.println("opção \n1 = Aceitar\n2 = Correr");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    if (escolhaJogador2 == 1) {
                        // Jogada do jogador1
                        System.out.println(
                                nomeJogador1
                                        + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                        exibirCartas(jogador1);
                        escolhaJogador1 = nomex1.nextInt();
                        Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                        System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                        System.out.println("+---------------------------------------------------------------+");
                        System.out.println();
                        System.out.println();
                        // Jogada do jogador2
                        System.out.println(
                                nomeJogador2
                                        + "\nescolha uma carta (digite 0, 1 ou 2):");
                        exibirCartas(jogador2);
                        escolhaJogador2 = nomex1.nextInt();
                        Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                        System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                        System.out.println(
                                "+---------------------------------------------------------------+\n\n");
                        // Determina o vencedor da rodada pelo ID da carta
                        if (cartaJogadaJogador1.getID() > cartaJogadaJogador2.getID()) {
                            pontosJogador1 = pontosJogador1 + 3;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador1);
                        } else if (cartaJogadaJogador1.getID() < cartaJogadaJogador2.getID()) {
                            pontosJogador2 = pontosJogador2 + 3;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador2);
                        } else {
                            System.out.println("Empate na rodada " + rodada);
                        }

                    }

                    break;
                case 6:
                    System.out.println("Atenção !!!");
                    System.out.println(jogador1 + " Pediu truco valendo" + truco + " Pontos nessa rodada!");
                    System.out.println("opção \n1 = Aceitar\n2 = Correr");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    if (escolhaJogador2 == 1) {
                        // Jogada do jogador1
                        System.out.println(
                                nomeJogador1
                                        + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                        exibirCartas(jogador1);
                        escolhaJogador1 = nomex1.nextInt();
                        Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                        System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                        System.out.println("+---------------------------------------------------------------+");
                        System.out.println();
                        System.out.println();
                        // Jogada do jogador2
                        System.out.println(
                                nomeJogador2
                                        + "\nescolha uma carta (digite 0, 1 ou 2):");
                        exibirCartas(jogador2);
                        escolhaJogador2 = nomex1.nextInt();
                        Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                        System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                        System.out.println(
                                "+---------------------------------------------------------------+\n\n");
                        // Determina o vencedor da rodada pelo ID da carta
                        if (cartaJogadaJogador1.getID() > cartaJogadaJogador2.getID()) {
                            pontosJogador1 = pontosJogador1 + 6;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador1);
                        } else if (cartaJogadaJogador1.getID() < cartaJogadaJogador2.getID()) {
                            pontosJogador2 = pontosJogador2 + 6;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador2);
                        } else {
                            System.out.println("Empate na rodada " + rodada);
                        }

                    }
                    break;

                case 9:
                    System.out.println("Atenção !!!");
                    System.out.println(jogador1 + " Pediu truco valendo" + truco + " Pontos nessa rodada!");
                    System.out.println("opção \n1 = Aceitar\n2 = Correr");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    if (escolhaJogador2 == 1) {
                        // Jogada do jogador1
                        System.out.println(
                                nomeJogador1
                                        + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                        exibirCartas(jogador1);
                        escolhaJogador1 = nomex1.nextInt();
                        Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                        System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                        System.out.println("+---------------------------------------------------------------+");
                        System.out.println();
                        System.out.println();
                        // Jogada do jogador2
                        System.out.println(
                                nomeJogador2
                                        + "\nescolha uma carta (digite 0, 1 ou 2):");
                        exibirCartas(jogador2);
                        escolhaJogador2 = nomex1.nextInt();
                        Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                        System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                        System.out.println(
                                "+---------------------------------------------------------------+\n\n");
                        // Determina o vencedor da rodada pelo ID da carta
                        if (cartaJogadaJogador1.getID() > cartaJogadaJogador2.getID()) {
                            pontosJogador1 = pontosJogador1 + 9;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador1);
                        } else if (cartaJogadaJogador1.getID() < cartaJogadaJogador2.getID()) {
                            pontosJogador2 = pontosJogador2 + 9;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador2);
                        } else {
                            System.out.println("Empate na rodada " + rodada);
                        }

                    }
                    break;
                case 12:
                    System.out.println("Atenção !!!");
                    System.out.println(jogador1 + " Pediu truco valendo" + truco + " Pontos nessa rodada!");
                    System.out.println("opção \n1 = Aceitar\n2 = Correr");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    if (escolhaJogador2 == 1) {
                        // Jogada do jogador1
                        System.out.println(
                                nomeJogador1
                                        + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                        exibirCartas(jogador1);
                        escolhaJogador1 = nomex1.nextInt();
                        Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                        System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                        System.out.println("+---------------------------------------------------------------+");
                        System.out.println();
                        System.out.println();
                        // Jogada do jogador2
                        System.out.println(
                                nomeJogador2
                                        + "\nescolha uma carta (digite 0, 1 ou 2):");
                        exibirCartas(jogador2);
                        escolhaJogador2 = nomex1.nextInt();
                        Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                        System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                        System.out.println(
                                "+---------------------------------------------------------------+\n\n");
                        // Determina o vencedor da rodada pelo ID da carta
                        if (cartaJogadaJogador1.getID() > cartaJogadaJogador2.getID()) {
                            pontosJogador1 = pontosJogador1 + 12;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador1);
                        } else if (cartaJogadaJogador1.getID() < cartaJogadaJogador2.getID()) {
                            pontosJogador2 = pontosJogador2 + 12;
                            System.out.println(
                                    "\n\n+---------------------------------------------------------------+");
                            System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                            System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                            System.out.println("Vencedor da rodada: " + nomeJogador2);
                        } else {
                            System.out.println("Empate na rodada " + rodada);
                        }

                    }
                    break;

                default:
                    break;
            }

        }

        nomex1.close();
    }

    public static void pedirTruco2(String nomeJogador1, String nomeJogador2, Jogador jogador1, Jogador jogador2,
            int pontosJogador1, int pontosJogador2, int rodada) {
        int escolhaJogador1 = 0;
        int escolhaJogador2 = 0;
        Scanner nomex1 = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println(
                "|  " + jogador2.getNome() + ", você pediu truco: escolha quantos pontos essa rodada vai ter |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("Digite '3', '6', '9' ou '12'");
        int truco = nomex1.nextInt();
        System.out.println("Atenção !!!");
        System.out.println(jogador2.getNome() + " Pediu truco valendo " + truco + " pontos nessa rodada!");
        System.out.println("Opções:");
        System.out.println("1 = Aceitar");
        System.out.println("2 = Correr");
        int respostaJogador1 = nomex1.nextInt();

        if (respostaJogador1 == 1) {
            switch (truco) {
                case 3:
                    // Jogada do jogador2
                    System.out.println(nomeJogador2 + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    Carta cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println();
                    System.out.println();

                    // Jogada do jogador1
                    System.out.println(nomeJogador1 + "\nEscolha uma carta (digite 0, 1 ou 2):");
                    exibirCartas(jogador1);
                    escolhaJogador1 = nomex1.nextInt();
                    Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println("+---------------------------------------------------------------+\n\n");

                    // Determina o vencedor da rodada pelo ID da carta
                    if (cartaJogadaJogador2.getID() > cartaJogadaJogador1.getID()) {
                        pontosJogador2 += 3;
                        System.out.println("Vencedor da rodada: " + nomeJogador2);
                    } else if (cartaJogadaJogador2.getID() < cartaJogadaJogador1.getID()) {
                        pontosJogador1 += 3;
                        System.out.println("Vencedor da rodada: " + nomeJogador1);
                    } else {
                        System.out.println("Empate na rodada " + rodada);
                    }
                    break;

                case 6:
                    // Jogada do jogador2
                    System.out.println(nomeJogador2 + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println();
                    System.out.println();

                    // Jogada do jogador1
                    System.out.println(nomeJogador1 + "\nEscolha uma carta (digite 0, 1 ou 2):");
                    exibirCartas(jogador1);
                    escolhaJogador1 = nomex1.nextInt();
                    cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println("+---------------------------------------------------------------+\n\n");

                    // Determina o vencedor da rodada pelo ID da carta
                    if (cartaJogadaJogador2.getID() > cartaJogadaJogador1.getID()) {
                        pontosJogador2 += 6;
                        System.out.println("Vencedor da rodada: " + nomeJogador2);
                    } else if (cartaJogadaJogador2.getID() < cartaJogadaJogador1.getID()) {
                        pontosJogador1 += 6;
                        System.out.println("Vencedor da rodada: " + nomeJogador1);
                    } else {
                        System.out.println("Empate na rodada " + rodada);
                    }
                    break;
                case 9:
                    // Jogada do jogador2
                    System.out.println(nomeJogador2 + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println();
                    System.out.println();

                    // Jogada do jogador1
                    System.out.println(nomeJogador1 + "\nEscolha uma carta (digite 0, 1 ou 2):");
                    exibirCartas(jogador1);
                    escolhaJogador1 = nomex1.nextInt();
                    cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println("+---------------------------------------------------------------+\n\n");

                    // Determina o vencedor da rodada pelo ID da carta
                    if (cartaJogadaJogador2.getID() > cartaJogadaJogador1.getID()) {
                        pontosJogador2 += 9;
                        System.out.println("Vencedor da rodada: " + nomeJogador2);
                    } else if (cartaJogadaJogador2.getID() < cartaJogadaJogador1.getID()) {
                        pontosJogador1 += 9;
                        System.out.println("Vencedor da rodada: " + nomeJogador1);
                    } else {
                        System.out.println("Empate na rodada " + rodada);
                    }
                    break;
                case 12:
                    // Jogada do jogador2
                    System.out.println(nomeJogador2 + ", escolha uma carta para jogar (digite 0, 1 ou 2):");
                    exibirCartas(jogador2);
                    escolhaJogador2 = nomex1.nextInt();
                    cartaJogadaJogador2 = jogador2.getMao().remove(escolhaJogador2);
                    System.out.println(nomeJogador2 + " jogou: " + cartaJogadaJogador2);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println();
                    System.out.println();

                    // Jogada do jogador1
                    System.out.println(nomeJogador1 + "\nEscolha uma carta (digite 0, 1 ou 2):");
                    exibirCartas(jogador1);
                    escolhaJogador1 = nomex1.nextInt();
                    cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                    System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                    System.out.println("+---------------------------------------------------------------+\n\n");

                    // Determina o vencedor da rodada pelo ID da carta
                    if (cartaJogadaJogador2.getID() > cartaJogadaJogador1.getID()) {
                        pontosJogador2 += 12;
                        System.out.println("Vencedor da rodada: " + nomeJogador2);
                    } else if (cartaJogadaJogador2.getID() < cartaJogadaJogador1.getID()) {
                        pontosJogador1 += 12;
                        System.out.println("Vencedor da rodada: " + nomeJogador1);
                    } else {
                        System.out.println("Empate na rodada " + rodada);
                    }
                    break;

                default:
                    System.out.println("Opção inválida! A partida continua.");
                    break;
            }
        } else if (respostaJogador1 == 2) {
            System.out.println(jogador1.getNome() + " decidiu correr. " + jogador2.getNome() + " ganhou a partida!");
            pontosJogador2 += 3;
            exibirPlacarFinal(nomeJogador1, pontosJogador1, 0, nomeJogador2, pontosJogador2, 1, rodada);
        } else {
            System.out.println("Opção inválida! A partida continua.");
        }

        nomex1.close();
    }

}
// -- Final arquivo Mesa1.java