public class Carta {
    private String[] naipes = { "Ouros", "Espadas", "Copas", "Paus" };
    private String[] valorCartas = { "4", "5", "6", "7", "Dama", "Valete", "Rei", "Ás", "2", "3" };

    public String[] getNaipes() {
        return naipes;
    }

    public String[] getValorCartas() {
        return valorCartas;
    }

    @Override
    public String toString() {
        return valorCartas + " de " + naipes;
    }

}