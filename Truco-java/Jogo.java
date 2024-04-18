import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        int gameLoop = 1;
        Scanner sc = new Scanner(System.in);
        while (gameLoop != 0) {
            int opc = 0;
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|\t\tBem Vindo ao Jogo Truco com Amigos\t\t|");
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|\tOpções para jogar\t\t\t\t\t|");
            System.out.println("|\t\t\t\t\t\t\t\t|");
            System.out.println("|\t1 = 1 Player vs 1 Maquina\t\t\t\t|");
            System.out.println("|\t2 = 2 Player vs 2 Maquina\t\t\t\t|");
            System.out.println("|\t3 = 2 Player vs 2 Player\t\t\t\t|");
            System.out.println("|\t0 = Sair do Jogo\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------+");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 0:
                    System.out.println("+-------------------------------+");
                    System.out.println("|\tQuer mesmo sair do jogo\t|");
                    System.out.println("+-------------------------------+");
                    System.out.println("|\t0 = Sim\t\t\t|");
                    System.out.println("|\t1 = Não\t\t\t|");
                    System.out.println("+-------------------------------+");
                    gameLoop = sc.nextInt();
                    sc.nextLine();
                    if (gameLoop == 1) {
                        continue;

                    } else {
                        gameLoop = 0;
                    }

                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|\t\tErro! Opção não aceita tente novamente\t\t|");
                    System.out.println("+---------------------------------------------------------------+");
                    break;
            }

        }
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|\t\tObrigado por jogar!\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------+");
        sc.close();
    }

}
// jogador1 deve embaralhar e dar o monte para o jogador2
// jogador2 deve decidir puxar uma, duas ou três cartas aleatorias que vier
// após isso o jogador2 devolve o monte para o jogador1 para entregar os
// restantes das cartas
// lembrando que o máximo de cartas por jogador são 3
// caso o jogador2 tenha puxado alguma das 3 opções, ele verá as cartas e
// decidir quantas cartas vai para o seu parceiro o jogador4, se ele puxo 2 ele
// pode escolher ficar com as duas, dar as duas para o parceiro, ou escolher
// ficar com uma delas e dar a outra para o parceiro
// após todos receber as 3 cartas, o jogador1 tem que colocar uma carta do
// baralho para ser o vira "Carta coringa pra ver qual a carta que está valendo
// no jogo"
// jogador deve lancar uma carta a cada jogada
// jogador deve receber um ponto a cada partida ganha
// maximo de pontos e 12