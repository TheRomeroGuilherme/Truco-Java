import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayList<>();
        criarBaralho();
    }

    private void criarBaralho() {
        String[] naipes = { "Ouros", "Espadas", "Copas", "Paus" };
        String[] valores = { "4", "5", "6", "7", "Dama", "Valete", "Rei", "Ás", "2", "3" };

        for (String naipe : naipes) {
            for (String valor : valores) {
                Carta carta = new Carta(valor, naipe);
                cartas.add(carta);
            }
        }
    }

    public Carta darCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);
        } else {
            return null; // Retorna null se o baralho estiver vazio
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
}
