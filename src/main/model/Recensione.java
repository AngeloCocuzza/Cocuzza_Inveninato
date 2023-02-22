package model;

public class Recensione {
    private Integer voto;
    private String commento;

    public Recensione(Integer voto, String commento) {
        this.voto = voto;
        this.commento = commento;
    }

    public Recensione() {
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
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
