import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    ArrayList<Carta> cartas = new ArrayList<Carta>();

    public Baralho() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                String value;
                String naipes;
                switch (i) {
                    case 0:
                        value = escolher(j);
                        naipes = "Ouro";
                        Carta cartaO = new Carta(value, naipes);
                        cartas.add(cartaO);
                        break;
                    case 1:
                        value = escolher(j);
                        naipes = "Espadas";
                        Carta cartaE = new Carta(value, naipes);
                        cartas.add(cartaE);
                        break;
                    case 2:
                        value = escolher(j);
                        naipes = "Copas";
                        Carta cartaC = new Carta(value, naipes);
                        cartas.add(cartaC);
                        break;
                    case 3:
                        value = escolher(j);
                        naipes = "Paus";
                        Carta cartaP = new Carta(value, naipes);
                        cartas.add(cartaP);
                        break;
                    default:
                        break;
                }
            }

        }
    }

    public String escolher(int J) {
        switch (J) {
            case 0:
                return "A";

            case 1:
                return "2";

            case 2:
                return "3";

            case 3:
                return "4";

            case 4:
                return "5";

            case 5:
                return "6";

            case 6:
                return "7";

            case 7:
                return "J";

            case 8:
                return "Q";

            case 9:
                return "K";

            default:
                break;
        }
        return "";
    }

    // para embaralhar conteúdo dentro da ArrayList
    public void embaralhar() {
        Collections.shuffle(cartas);
    }

}
