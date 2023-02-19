package model;

public class Recensione {
    private int voto;
    private String commento;

    public Recensione(Integer voto, String commento) {
        this.voto = voto;
        this.commento = commento;
    }

    public Recensione() {
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    @Override
    public String toString() {
        return "Recensione{" +
                "voto=" + voto +
                ", commento='" + commento + '\'' +
                '}';
    }
}
