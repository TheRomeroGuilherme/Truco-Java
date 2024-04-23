
//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo Mesa2.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mesa2 {

    public static void exibirCartas(Jogador jogador) {
        System.out.println("Cartas de " + jogador.getNome() + ":");
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println(i + ": " + jogador.getMao().get(i));
        }
    }

    public static void iniciarJogo2() {
        Scanner Nome2x2 = new Scanner(System.in);

        // Pedir o nome dos jogadores
        System.out.println("Digite o nome do jogador 1:");
        String nomeJogador1 = Nome2x2.nextLine();

        System.out.println("Digite o nome do jogador 2:");
        String nomeJogador2 = Nome2x2.nextLine();

        System.out.println("Digite o nome do jogador 3:");
        String nomeJogador3 = Nome2x2.nextLine();

        System.out.println("Digite o nome do jogador 4:");
        String nomeJogador4 = Nome2x2.nextLine();

        // Chamar o método iniciarJogo2x2 com os nomes dos jogadores
        iniciarJogo2x2(nomeJogador1, nomeJogador2, nomeJogador3, nomeJogador4);

        Nome2x2.close();
    }

    public static void iniciarJogo2x2(String nomeJogador1, String nomeJogador2, String nomeJogador3,
            String nomeJogador4) {
        Scanner Nomex2 = new Scanner(System.in);
        int valorPartidaDupla1 = 0;
        int valorPartidaDupla2 = 0;
        int wi = 1;
        while (wi != 0) {
            int pontosDupla1 = 0;
            int pontosDupla2 = 0;
            Carta cartaMaisForte = null;
            Jogador jogadorQueJogouCartaMaisForte = null;

            valorPartidaDupla1 = 0;
            valorPartidaDupla2 = 0;

            // Criação do baralho e embaralhamento
            Baralho baralho = new Baralho();
            baralho.embaralhar();

            // Distribuição de cartas para os jogadores
            List<Jogador> jogadores = new ArrayList<>();
            jogadores.add(new Jogador(nomeJogador1));
            jogadores.add(new Jogador(nomeJogador2));
            jogadores.add(new Jogador(nomeJogador3));
            jogadores.add(new Jogador(nomeJogador4));

            for (Jogador jogador : jogadores) {
                for (int i = 0; i < 3; i++) {
                    jogador.receberCarta(baralho.darCarta());
                }
            }
            System.out.println();
            System.out.println();
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
            System.out.println();
            System.out.println();

            // Laço para a contagem de rodadas
            for (int rodada = 1; rodada <= 3; rodada++) {
                System.out.println();
                System.out.println();
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|\tRodada: " + rodada + "|");
                System.out.println("+---------------------------------------------------------------+");
                System.out.println();

                // Rodada 1
                for (Jogador jogador : jogadores) {

                    System.out.println(jogador.getNome()
                            + ", escolha uma carta para jogar (digite 0, 1 ou 2 para atacar a carta na mesa):");
                    exibirCartas(jogador);
                    if (Nomex2.hasNextInt()) { // Verifica se há um inteiro disponível
                        int escolha = Nomex2.nextInt();
                        Carta cartaJogada = jogador.getMao().remove(escolha);
                        System.out.println(jogador.getNome() + " jogou: " + cartaJogada);

                        // Se a carta jogada for mais forte do que a carta anteriormente jogada,
                        // atualize
                        if (cartaMaisForte == null || cartaJogada.getID() > cartaMaisForte.getID()) {
                            cartaMaisForte = cartaJogada;
                            jogadorQueJogouCartaMaisForte = jogador;
                        }
                    } else {
                        System.out.println("Entrada inválida. Digite um número válido.");
                        // Adicione o código para lidar com a entrada inválida, se necessário
                    }
                    System.out.println();
                    System.out.println();
                }

                // Determina a dupla vencedora da rodada
                if (jogadorQueJogouCartaMaisForte == jogadores.get(0)
                        || jogadorQueJogouCartaMaisForte == jogadores.get(1)) {
                    // Pontos para a Dupla 1
                    pontosDupla1++;
                    System.out.println("Vencedor da rodada: " + nomeJogador1 + " e " + nomeJogador2);
                } else {
                    // Pontos para a Dupla 2
                    pontosDupla2++;
                    System.out.println("Vencedor da rodada: " + nomeJogador3 + " e " + nomeJogador4);
                }

                // Restitui a carta mais forte ao baralho
                baralho.adicionarCarta(cartaMaisForte);
                System.out.println();
                System.out.println();
            }
            contabilizarPontosDuplas(pontosDupla1, pontosDupla2, valorPartidaDupla1, valorPartidaDupla2,
                    nomeJogador1 + " e " + nomeJogador2, nomeJogador3 + " e " + nomeJogador4);
        }

        Nomex2.close();
    }

    public static void contabilizarPontosDuplas(int pontosDupla1, int pontosDupla2, int valorPartidaDupla1,
            int valorPartidaDupla2, String nomeDupla1, String nomeDupla2) {

        if (pontosDupla1 > pontosDupla2) {
            valorPartidaDupla1++;
        } else if (pontosDupla1 < pontosDupla2) {
            valorPartidaDupla2++;
        }
        System.out.println();
        System.out.println();
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("Placar rodada: " + nomeDupla1 + " " + pontosDupla1 + " x " + pontosDupla2
                + " " + nomeDupla2);
        System.out.println("+---------------------------------------------------------------+");

        // Exibe o placar total
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("Placar Total: " + nomeDupla1 + " " + valorPartidaDupla1 + " x " + valorPartidaDupla2
                + " " + nomeDupla2);
        System.out.println("+---------------------------------------------------------------+");

        // Verifica se uma das duplas alcançou 12 pontos na partida
        if (valorPartidaDupla1 >= 12 || valorPartidaDupla2 >= 12) {
            System.out.println("Jogo encerrado!");
            if (valorPartidaDupla1 > valorPartidaDupla2) {
                System.out.println("Vencedor: " + nomeDupla1);
            } else if (valorPartidaDupla1 < valorPartidaDupla2) {
                System.out.println("Vencedor: " + nomeDupla2);
            } else {
                System.out.println("Empate!");
            }
        }
    }

    public static void devolverCartaAoBaralho(Baralho baralho, Carta carta) {
        baralho.adicionarCarta(carta); // Adiciona a carta de volta ao baralho
    }

}

// -- Final arquivo Mesa2.java