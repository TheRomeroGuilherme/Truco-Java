import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|\t\tBem Vindo ao Jogo Truco com Amigos\t\t|");
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|\tOpções para jogar\t\t\t\t\t|");
            System.out.println("|\t\t\t\t\t\t\t\t|");
            System.out.println("|\t1 = 1 Player vs 1 Player\t\t\t\t|");
            System.out.println("|\t0 = Sair do Jogo\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------+");
            int opc = sc.nextInt();

            switch (opc) {
                case 0:
                    System.out.println("+-------------------------------+");
                    System.out.println("|\tQuer mesmo sair do jogo\t|");
                    System.out.println("+-------------------------------+");
                    System.out.println("|\t0 = Sim\t\t\t|");
                    System.out.println("|\t1 = Não\t\t\t|");
                    System.out.println("+-------------------------------+");
                    int confirmacaoSaida = sc.nextInt();
                    if (confirmacaoSaida == 1) {
                        continue;
                    } else {
                        System.out.println("+---------------------------------------------------------------+");
                        System.out.println("|\t\tObrigado por jogar!\t\t\t\t|");
                        System.out.println("+---------------------------------------------------------------+");
                        sc.close();
                        return;
                    }
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    System.out.println("Opção desejada está em construção");
                    break;
                default:
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|\t\tErro! Opção não aceita tente novamente\t\t|");
                    System.out.println("+---------------------------------------------------------------+");
                    break;
            }
        }
    }

    public static void iniciarJogo() {
        // Criar instâncias de Jogador e Maquina
        Jogador jogador1 = new Jogador("Player1");
        Jogador jogador2 = new Jogador("Player2");

        // Criar um baralho e embaralhá-lo
        Baralho baralho = new Baralho();
        baralho.embaralhar();

        // Distribuir cartas para os jogadores
        for (int i = 0; i < 3; i++) {
            jogador1.receberCarta(baralho.darCarta());
            jogador2.receberCarta(baralho.darCarta());
        }

        // Exibir as mãos do jogador e da máquina
        System.out.println("Mão do Jogador1: " + jogador1.getMao());
        System.out.println("Mão do Jogador2: " + jogador2.getMao());
    }
}
