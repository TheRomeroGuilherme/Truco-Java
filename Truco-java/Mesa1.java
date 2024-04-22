
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
                System.out.println("|\tRodada: " + rodada + "|");

                // Jogada do jogador1
                System.out.println(
                        nomeJogador1
                                + ", escolha uma carta para jogar (digite 0, 1 ou 2 para atacar a carta na mesa):");
                exibirCartas(jogador1);
                int escolhaJogador1 = nomex1.nextInt();
                Carta cartaJogadaJogador1 = jogador1.getMao().remove(escolhaJogador1);
                System.out.println(nomeJogador1 + " jogou: " + cartaJogadaJogador1);
                System.out.println("+---------------------------------------------------------------+");

                // Jogada do jogador2
                System.out.println(
                        nomeJogador2
                                + ", escolha uma carta para jogar (digite 0, 1 ou 2 para atacar a carta na mesa):");
                exibirCartas(jogador2);
                int escolhaJogador2 = nomex1.nextInt();
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

        if (pontosJogador1 > pontosJogador2) {
            valorPartidaJogador1++;
        } else if (pontosJogador1 < pontosJogador2) {
            valorPartidaJogador2++;
        }

        System.out.println("\n\n+---------------------------------------------------------------+");
        System.out.println(
                "Placar rodada: " + nomeJogador1 + " " + pontosJogador1 + " x " + pontosJogador2
                        + " " + nomeJogador2);
        System.out.println("+---------------------------------------------------------------+");

        // Verifica se um dos jogadores alcançou 12 pontos na ValorPartida
        if (valorPartidaJogador1 >= 12 || valorPartidaJogador2 >= 12) {
            System.out.println("Jogo encerrado!");
            if (valorPartidaJogador1 > valorPartidaJogador2) {
                System.out.println("Vencedor: " + nomeJogador1);
                // Exibe o placar
                System.out.println("\n\n\n+---------------------------------------------------------------+");
                System.out.println(
                        "Placar Total: " + nomeJogador1 + " " + valorPartidaJogador1 + " x " + valorPartidaJogador2
                                + " " + nomeJogador2);
                System.out.println("+---------------------------------------------------------------+\n\n\n");
            } else if (valorPartidaJogador1 < valorPartidaJogador2) {
                System.out.println("Vencedor: " + nomeJogador2);
                // Exibe o placar
                System.out.println("\n\n\n+---------------------------------------------------------------+");
                System.out.println(
                        "Placar Total: " + nomeJogador1 + " " + valorPartidaJogador1 + " x " + valorPartidaJogador2
                                + " " + nomeJogador2);
                System.out.println("+---------------------------------------------------------------+\n\n\n");
            } else {
                System.out.println("Empate!");
            }
        }

    }

    public static void devolverCartaAoBaralho(Baralho baralho, Carta carta) {
        baralho.adicionarCarta(carta); // Adiciona a carta de volta ao baralho
    }

}
// -- Final arquivo Mesa1.java