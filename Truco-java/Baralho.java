import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    private ArrayList<Carta> cartas;

    public Baralho() {
        this.cartas = new ArrayList<>();
        criarBaralho();
    }

    private void criarBaralho() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                Carta carta = new Carta();
                cartas.add(carta);
                // para ver as cartas em ordem
                // System.out.println(
                // "Carta #" + (cartas.size() - 0) +
                // " Naipe: [" + carta.getNaipes()[j] + "]" +
                // " Valor: [" + carta.getValorCartas()[i] + "]");
            }

        }
    }

    public String[] getNaipesDeUmaCarta(Carta carta) {
        return carta.getNaipes();
    }

    public String[] getValorCartasDeUmaCarta(Carta carta) {
        return carta.getValorCartas();
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }
}