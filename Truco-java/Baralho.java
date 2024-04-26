
//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo baralho.java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Baralho {
    private List<Carta> cartas;
    private Random random;

    public Baralho() {
        this.cartas = new ArrayList<>();
        this.random = new Random();
        criarBaralho();
    }

    private void criarBaralho() {
        String[] naipes = { "Ouros", "Espadas", "Copas", "Paus" };
        String[] valores = { "4", "5", "6", "7", "Dama", "Valete", "Rei", "Ás", "2", "3" };
        int id = 1;

        for (String valor : valores) {
            for (String naipe : naipes) {
                Carta carta = new Carta(valor, naipe, id++);
                cartas.add(carta);
                // System.out.println(carta);
            }
        }
    }

    public Carta darCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);
        } else {
            return null;
        }
    }

    public Carta getCartaVira() {
        if (!cartas.isEmpty()) {
            return cartas.get(cartas.size() - 1); // Retorna a última carta do baralho como a carta vira
        } else {
            return null; // Retorna null se o baralho estiver vazio
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta virarCarta() {
        if (!cartas.isEmpty()) {
            Random random = new Random();
            int indice = random.nextInt(cartas.size());
            Carta cartaVirada = cartas.remove(indice);
            return cartaVirada;
        } else {
            System.out.println("Erro: O baralho está vazio!");
            return null;
        }
    }

    public void exibirCartas(Jogador jogador) {
        System.out.println("Cartas de " + jogador.getNome() + ":");
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println(i + ": " + jogador.getMao().get(i).toString());
        }
    }

    // Função para obter o próximo valor na sequência
    public static String proximoValor(String valor) {
        String[] valores = { "4", "5", "6", "7", "Dama", "Valete", "Rei", "Ás", "2", "3" };
        int indexAtual = Arrays.asList(valores).indexOf(valor);
        int proximoIndex = (indexAtual + 1) % valores.length;
        return valores[proximoIndex];
    }

    // Função para obter o próximo naipe na sequência
    public static String proximoNaipe(String naipe) {
        String[] naipes = { "Ouros", "Espadas", "Copas", "Paus" };
        int indexAtual = Arrays.asList(naipes).indexOf(naipe);
        int proximoIndex = (indexAtual + 1) % naipes.length;
        return naipes[proximoIndex];
    }

    public static int compararCartas(Carta carta1, Carta carta2) {
        String[] valores = { "4", "5", "6", "7", "Q", "J", "K", "A", "2", "3" };
        int indiceCarta1 = java.util.Arrays.asList(valores).indexOf(carta1.getValor());
        int indiceCarta2 = java.util.Arrays.asList(valores).indexOf(carta2.getValor());
        return Integer.compare(indiceCarta1, indiceCarta2);
    }

    public void adicionarCarta(Carta carta) {
        cartas.add(carta); // Adiciona a carta de volta ao baralho
    }
}

// --Final arquivo baralho.java