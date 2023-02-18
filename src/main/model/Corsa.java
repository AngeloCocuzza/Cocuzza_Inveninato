package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class Corsa extends CorsaViaggio implements Discount {
    private Utente utente;

    public Corsa(Integer ID, Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, Utente utente) {
        super(ID, autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.utente = utente;
    }

    public Corsa(Utente utente) {
        this.utente = utente;
    }

    public Corsa(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo, Utente utente) {
        super(autista, veicolo, data_partenza, ora_partenza, address, prezzo);
        this.utente = utente;
    }

    public Corsa(Autista autista, Date data_partenza, LocalTime ora_partenza, Address address) {
        super(autista, data_partenza, ora_partenza, address);
    }





    public Corsa(Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address) {
        super(veicolo,data_partenza,ora_partenza,address);
    }

    public Corsa() {
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /*public String getCitta_partenza() {
        return citta_partenza;
    }

    public void setCitta_partenza(String citta_partenza) {
        this.citta_partenza = citta_partenza;
    }

    public String getCitta_destinazione() {
        return citta_destinazione;
    }

    public void setCitta_destinazione(String citta_destinazione) {
        this.citta_destinazione = citta_destinazione;
    }*/

    /*public String getInidirizzo_partenza() {
        return inidirizzo_partenza;
    }

    public void setInidirizzo_partenza(String inidirizzo_partenza) {
        this.inidirizzo_partenza = inidirizzo_partenza;
    }

    public String getIndirizzo_destinazione() {
        return indirizzo_destinazione;
    }

    public void setIndirizzo_destinazione(String indirizzo_destinazione) {
        this.indirizzo_destinazione = indirizzo_destinazione;
    } */



    /*public Integer getKm_corsa() {
        return km_corsa;
    }

    public void setKm_corsa(Integer km_corsa) {
        this.km_corsa = km_corsa;
    }*/

    @Override
    public void setPrezzo() {
        super.setPrezzo(getFee());
    }

    public float getFee(){
        float prezzotot=0;
        if(getAddress().getCitta_partenza().equals(getAddress().getCitta_destinazione())) {
            prezzotot=3*getAddress().getKm_corsa();
        }
        else {
            prezzotot= (float) (1.5*getAddress().getKm_corsa());
        }
        prezzotot=getDiscount(prezzotot);
        return prezzotot;
    }

    @Override
    public float getDiscount(float prezzotot) {
        float discount=0;
        float prezzobase = prezzotot;
        //float maggiorazione=0;
        if((getOra_partenza()).isAfter(LocalTime.parse("23:00:00")) && (getOra_partenza()).isBefore(LocalTime.parse("04:59:59"))) {
            discount=-25;
            prezzobase-=prezzobase*(discount/100);

        }

        if(getAddress().getCitta_partenza().equals(getAddress().getCitta_destinazione()) != true){
            if(getAddress().getKm_corsa() >= 100) {
                int km = getAddress().getKm_corsa()-100;
                discount=15;
                float prezzokm = (float) (km * 1.5*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(getAddress().getCitta_partenza().equals(getAddress().getCitta_destinazione())) {
            if(getAddress().getKm_corsa() >= 10) {
                int km = getAddress().getKm_corsa()-10;
                discount=20;
                float prezzokm = (float) (km * 3*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(getVeicolo().getN_posti()>4) {
            prezzobase = (float) (prezzobase*(0.25*(getVeicolo().getN_posti())));
        }
        if(getAddress().getKm_corsa()<1){
            prezzobase = 2;}
        return prezzobase;
    }
}
