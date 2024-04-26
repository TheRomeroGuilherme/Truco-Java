
//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo Mesa1.java
import java.util.Scanner;

public class Mesa1 {
    private static int valorPartidaJogador1 = 0;
    private static int valorPartidaJogador2 = 0;

    public static void iniciarJogo1(Scanner sc) {
        System.out.println("Digite o nome do jogador 1:");
        String nomeJogador1 = sc.nextLine();
        System.out.println("Digite o nome do jogador 2:");
        String nomeJogador2 = sc.nextLine();
        iniciarJogo1x1(nomeJogador1, nomeJogador2, sc);

    }

    public static void iniciarJogo1x1(String nomeJogador1, String nomeJogador2, Scanner sc) {

        while (true) {
            int pontosJogador1 = 0;
            int pontosJogador2 = 0;
            Baralho baralho = new Baralho();
            baralho.embaralhar();
            Jogador jogador1 = new Jogador(nomeJogador1);
            Jogador jogador2 = new Jogador(nomeJogador2);
            for (int i = 0; i < 3; i++) {
                jogador1.receberCarta(baralho.darCarta());
                jogador2.receberCarta(baralho.darCarta());
            }
            Carta cartaVirada = baralho.virarCarta();
            System.out.println("Carta virada na mesa (manilha): " + cartaVirada);
            for (int rodada = 1; rodada <= 3; rodada++) {
                System.out.println("Rodada " + rodada);
                pontosJogador1 = realizarJogada(nomeJogador1, jogador1, nomeJogador2, jogador2, pontosJogador1);
                pontosJogador2 = realizarJogada(nomeJogador2, jogador2, nomeJogador1, jogador1, pontosJogador2);

                if (pontosJogador1 >= 2 || pontosJogador2 >= 2) {
                    break;
                }
            }
            if (pontosJogador1 > pontosJogador2) {
                valorPartidaJogador1++;
                System.out.println(nomeJogador1 + " ganhou a partida!");
            } else {
                valorPartidaJogador2++;
                System.out.println(nomeJogador2 + " ganhou a partida!");
            }
            System.out.println("Total de pontos:");
            System.out.println(nomeJogador1 + ": " + pontosJogador1);
            System.out.println(nomeJogador2 + ": " + pontosJogador2);
            System.out.println("Partidas ganhas:");
            System.out.println(nomeJogador1 + ": " + valorPartidaJogador1);
            System.out.println(nomeJogador2 + ": " + valorPartidaJogador2);

            if (valorPartidaJogador1 >= 12 || valorPartidaJogador2 >= 12) {

                break;
            }
        }
    }

    public static int realizarJogada(String nomeJogador1, Jogador jogador1, String nomeJogador2, Jogador jogador2,
            int pontosJogador) {
        Baralho baralho = new Baralho(); // Criando uma instância de Baralho
        Scanner sc = new Scanner(System.in);

        System.out.println(nomeJogador1 + ", escolha uma carta (digite 0, 1 ou 2) para trucar -1:");
        baralho.exibirCartas(jogador1); // Chamando o método exibirCartas da instância de Baralho
        int escolha = Integer.parseInt(sc.nextLine());

        Carta cartaJogada = jogador1.jogarCarta(escolha);
        System.out.println(nomeJogador1 + " jogou: " + cartaJogada);

        System.out.println(nomeJogador2 + ", escolha uma carta (digite 0, 1 ou 2) para trucar -1:");
        baralho.exibirCartas(jogador2); // Chamando o método exibirCartas da instância de Baralho
        int escolhaOponente = Integer.parseInt(sc.nextLine());

        Carta cartaOponente = jogador2.jogarCarta(escolhaOponente);
        System.out.println(nomeJogador2 + " jogou: " + cartaOponente);

        int resultado = Baralho.compararCartas(cartaJogada, cartaOponente);
        if (resultado == 1) {
            pontosJogador++;
            System.out.println(nomeJogador1 + " ganhou a rodada!\n\n\n");

        } else if (resultado == -1) {
            pontosJogador--;
            System.out.println(nomeJogador2 + " ganhou a rodada!\n\n\n");
        } else {
            System.out.println("Empate na rodada!\n\n\n");
        }
        return pontosJogador;
    }

}
// --Final arquivo Mesa1.java1
