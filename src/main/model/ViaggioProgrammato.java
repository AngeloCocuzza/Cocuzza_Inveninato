package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class ViaggioProgrammato extends CorsaViaggio implements Discount{
    private List <Utente> utentiPrenotati=new ArrayList<>();
    private String evento;
    private Integer postiDisponibili;

    public ViaggioProgrammato(int ID, Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, String evento, Integer postiDisponibili) {
        super(ID, autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
    }


    public ViaggioProgrammato(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, String evento, Integer postiDisponibili) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
    }
public ViaggioProgrammato(){}

    public ViaggioProgrammato(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, String evento) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.evento = evento;
    }

    public ViaggioProgrammato(Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, String evento, Integer postiDisponibili) {
        super(veicolo, data_partenza, ora_partenza, address);
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
    }

    public ViaggioProgrammato(Integer ID,Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, String evento, Integer postiDisponibili) {
        super(ID,veicolo, data_partenza, ora_partenza, address);
        this.evento = evento;
        this.postiDisponibili = postiDisponibili;
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

    public void setPostiDisponibili(Integer postiDisponibili) {
          this.postiDisponibili=postiDisponibili;
    }

    @Override
    public void setPrezzo() {
        super.setPrezzo(getDiscount(getPrezzo()));
    }

    public void setUtente(Utente utente) {
        this.utentiPrenotati.add(utente);
    }

    @Override
    public float getDiscount(float prezzo) {
        float discount=0;
        if(LocalDate.parse(String.valueOf(getData_partenza())).getDayOfWeek().equals(SATURDAY) || LocalDate.parse(String.valueOf(getData_partenza())).getDayOfWeek().equals(SUNDAY)) {
            discount=-15;
            prezzo-=prezzo*(discount/100);
        }
        return prezzo;
    }
}
