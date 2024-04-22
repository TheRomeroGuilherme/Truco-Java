
//GitHub TheRomeroGuilherme/Truco-Java
//--Arquivo Jogador.java
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    protected String nome;
    protected List<Carta> mao;
    protected int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.mao = new ArrayList<>();
        this.pontuacao = 0;
    }

    public void receberCarta(Carta carta) {
        if (mao.size() < 3) {
            mao.add(carta);
        } else {
            System.out.println("Erro: Mão do jogador está cheia!");
        }
    }

    public static void exibirCartas(Jogador jogador) {
        System.out.println("Cartas de " + jogador.getNome() + ":");
        for (int i = 0; i < jogador.getMao().size(); i++) {
            System.out.println(i + ": " + jogador.getMao().get(i));
        }
    }

    public void jogarCarta(Carta carta) {
        if (mao.contains(carta)) {
            mao.remove(carta);
        } else {
            System.out.println("Erro: Carta não está na mão do jogador!");
        }
    }

    public void adicionarPonto() {
        pontuacao++;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    @Override
    public String toString() {
        return nome + ": " + mao + " (Pontuação: " + pontuacao + ")";
    }

    public void limparMao() {
        mao.clear();
    }
}

// --Final arquivo Jogador.java