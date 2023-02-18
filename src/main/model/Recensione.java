package model;

public class Recensione {
    private int voto;
    private String commento;

    public Recensione(int voto, String commento) {
        this.voto = voto;
        this.commento = commento;
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
}
