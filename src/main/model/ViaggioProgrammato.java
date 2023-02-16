package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViaggioProgrammato extends Corsa{
    private List <Utente> utentiPrenotati=new ArrayList<>();
    private String evento;
    private Integer postiDisponibili;

    public ViaggioProgrammato(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, List<Utente> utentiPrenotati, String evento, Integer postiDisponibili) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.utentiPrenotati = utentiPrenotati;
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
    }

    public ViaggioProgrammato(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, String evento, Integer postiDisponibili) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
    }

    public ViaggioProgrammato(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, String evento) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "ViaggioProgrammato{" +
                "utentiPrenotati=" + utentiPrenotati +
                ", evento='" + evento + '\'' +
                ", postiDisponibili=" + postiDisponibili +
                '}';
    }

    public List<Utente> getUtentiPrenotati() {
        return utentiPrenotati;
    }

    public void setUtentiPrenotati(List<Utente> utentiPrenotati) {
        this.utentiPrenotati = utentiPrenotati;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Integer getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili() {
          postiDisponibili=this.getVeicolo().getN_posti();
    }
}
