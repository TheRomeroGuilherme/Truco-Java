//GitHub TheRomeroGuilherme/Truco-Java
//-- Arquivo Carta.java
public class Carta {
    private String valor;
    private String naipe;
    private int ID = 0;

    public Carta(String valor, String naipe, int ID) {
        this.valor = valor;
        this.naipe = naipe;
        this.ID = ID;

    }

    public String getValor() {
        return valor;
    }

    public int getID() {
        return ID;
    }

    public String getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        return "| Naipe: "
                + naipe + " | Valor: " + valor + "\n+------------------------+";

    }

}

// -- Final arquivo Carta.java