import java.util.Scanner;

public class Mesa1 {

    public static void iniciarJogo1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do jogador 1:");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do jogador 2:");
        String nomeJogador2 = scanner.nextLine();
        scanner.close();
        iniciarJogo1x1(nomeJogador1, nomeJogador2);
    }

    public static void iniciarJogo1x1(String nomeJogador1, String nomeJogador2) {
        Scanner scanner = new Scanner(System.in);
        int valorPartidaJogador1 = 0;
        int valorPartidaJogador2 = 0;
        int pontosJogador1;
        int pontosJogador2;
        Baralho baralho;
        Jogador jogador1;
        Jogador jogador2;
        int rodada = 0;

        while (true) {
            pontosJogador1 = 0;
            pontosJogador2 = 0;
            baralho = new Baralho();
            baralho.embaralhar();
            jogador1 = new Jogador(nomeJogador1);
            jogador2 = new Jogador(nomeJogador2);

            for (int i = 1; i <= 3; i++) {
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }
            Carta cartaVirada = baralho.virarCarta();
            String valorManilha = cartaVirada.getValor();
            String naipeManilha = cartaVirada.getNaipe();
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("Carta virada na mesa (manilha): " + cartaVirada);

            String valorManilhaSeguinte = Baralho.proximoValor(valorManilha);
            String naipeManilhaSeguinte = Baralho.proximoNaipe(naipeManilha);
            System.out.println("Próxima manilha: " + valorManilhaSeguinte + " de " + naipeManilhaSeguinte);

            for (rodada = 1; rodada <= 3; rodada++) {
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|\t\t\tRodada: " + rodada + "\t\t\t\t|");
                System.out.println("+---------------------------------------------------------------+");

                pontosJogador1 = realizarJogada(nomeJogador1, jogador1, nomeJogador2, jogador2, pontosJogador1,
                        pontosJogador2, scanner, rodada);
                pontosJogador2 = realizarJogada(nomeJogador2, jogador2, nomeJogador1, jogador1, pontosJogador2,
                        pontosJogador1, scanner, rodada);

                contabilizarPontos(pontosJogador1, pontosJogador2, valorPartidaJogador1, valorPartidaJogador2,
                        nomeJogador1, nomeJogador2, rodada);

                if (pontosJogador1 >= 12 || pontosJogador2 >= 12) {
                    break;
                }
            }
            if (pontosJogador1 >= 12 || pontosJogador2 >= 12) {
                break;
            }
        }
        scanner.close();
    }

    public static int realizarJogada(String nomeJogador, Jogador jogador, String nomeOponente, Jogador oponente,
            int pontosJogador, int pontosOponente, Scanner scanner, int rodada) {
        int escolha = 0;

        System.out.println(nomeJogador + ", escolha uma carta (digite 0, 1 ou 2) ou digite -1 para trucar:");
        exibirCartas(jogador);
        escolha = scanner.nextInt();
        scanner.nextLine();
        if (escolha == -1) {
            pedirTruco(nomeJogador, nomeOponente, jogador, oponente, pontosJogador, pontosOponente, scanner);
        } else {
            Carta cartaJogada = jogador.jogarCarta(escolha);
            System.out.println(nomeJogador + " jogou: " + cartaJogada);
            System.out.println(nomeOponente + ", escolha uma carta para jogar:");
            exibirCartas(oponente);
            int escolhaOponente = scanner.nextInt();
            scanner.nextLine();
            Carta cartaOponente = oponente.jogarCarta(escolhaOponente);
            System.out.println(nomeOponente + " jogou: " + cartaOponente);
            if (Baralho.compararCartas(cartaJogada, cartaOponente) == 1) {
                pontosJogador++;
                System.out.println(nomeJogador + " ganhou a rodada!");
            } else if (Baralho.compararCartas(cartaJogada, cartaOponente) == -1) {
                pontosOponente++;
                System.out.println(nomeOponente + " ganhou a rodada!");
            } else {
                System.out.println("Empate na rodada!");
            }
        }
        return pontosJogador;
    }

    public static void pedirTruco(String nomeJogador, String nomeOponente, Jogador jogador, Jogador oponente,
            int pontosJogador, int pontosOponente, Scanner scanner) {
        System.out.println(nomeJogador + " pediu truco!");
        System.out.println(nomeOponente + ", aceita? (s/n)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            System.out.println(nomeOponente + " aceitou o truco!");
            System.out.println(nomeJogador + ", você quer aumentar? (s/n)");
            String respostaAumento = scanner.nextLine();
            if (respostaAumento.equalsIgnoreCase("s")) {
                System.out.println(nomeJogador + " aumentou o truco!");
                System.out.println(nomeOponente + ", aceita? (s/n)");
                String respostaOponente = scanner.nextLine();
                if (respostaOponente.equalsIgnoreCase("s")) {
                    System.out.println(nomeOponente + " aceitou o aumento!");
                    pontosJogador += 3;
                    System.out.println(nomeJogador + " ganhou " + pontosJogador + " pontos!");
                } else {
                    System.out.println(nomeOponente + " desistiu do truco!");
                    pontosOponente += 3;
                    System.out.println(nomeOponente + " ganhou " + pontosOponente + " pontos!");
                }
            } else {
                System.out.println(nomeJogador + " desistiu do truco!");
                pontosOponente += 2;
                System.out.println(nomeOponente + " ganhou " + pontosOponente + " pontos!");
            }
        } else {
            System.out.println(nomeOponente + " desistiu do truco!");
            pontosJogador += 1;
            System.out.println(nomeJogador + " ganhou " + pontosJogador + " pontos!");
        }
    }

    public static void contabilizarPontos(int pontosJogador1, int pontosJogador2, int valorPartidaJogador1,
            int valorPartidaJogador2, String nomeJogador1, String nomeJogador2, int rodada) {
        if (pontosJogador1 >= 12 || pontosJogador2 >= 12) {
            if (pontosJogador1 > pontosJogador2) {
                System.out.println(nomeJogador1 + " ganhou a partida!");
                valorPartidaJogador1++;
            } else {
                System.out.println(nomeJogador2 + " ganhou a partida!");
                valorPartidaJogador2++;
            }
            System.out.println("Total de pontos:");
            System.out.println(nomeJogador1 + ": " + pontosJogador1);
            System.out.println(nomeJogador2 + ": " + pontosJogador2);
            System.out.println("Partidas ganhas:");
            System.out.println(nomeJogador1 + ": " + valorPartidaJogador1);
            System.out.println(nomeJogador2 + ": " + valorPartidaJogador2);
            System.out.println("+---------------------------------------------------------------+");
        } else {
            System.out.println("Pontuação após a rodada " + rodada + ":");
            System.out.println(nomeJogador1 + ": " + pontosJogador1);
            System.out.println(nomeJogador2 + ": " + pontosJogador2);
        }
    }

    public static void exibirCartas(Jogador jogador) {
        System.out.println("Suas cartas:");
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println(i + ": " + jogador.getMao().get(i));
        }
    }

    public static void main(String[] args) {
        iniciarJogo1();
    }
}
