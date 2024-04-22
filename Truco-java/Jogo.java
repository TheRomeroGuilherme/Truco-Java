//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo Jogo.java

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
            System.out.println("|\t2 = 2 Players vs 2s Player\t\t\t\t|");

            System.out.println("|\t0 = Sair do Jogo\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------+");
            int opc = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha pendente

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
                    Mesa1.iniciarJogo1();
                    break;
                case 2:
                    Mesa2.iniciarJogo2();
                    break;
                default:
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|\t\tErro! Opção não aceita tente novamente\t\t|");
                    System.out.println("+---------------------------------------------------------------+");
                    break;
            }
        }
    }
}

// --Final arquivo Jogo.java1