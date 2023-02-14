package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class Corsa implements Discount {
    private Autista autista;
    private Utente utente;
    private Veicolo veicolo;
    private String citta_partenza;
    private String citta_destinazione;
    private Date data_partenza;
    private String inidirizzo_partenza;
    private String indirizzo_destinazione;
    private LocalTime ora_partenza;

    private Integer km_corsa;
    private float prezzo;


    public Corsa(Autista autista, Utente utente, Veicolo veicolo, String citta_partenza, String citta_destinazione, Date data_partenza, String inidirizzo_partenza, String indirizzo_destinazione, LocalTime ora_partenza, float prezzo) {
        this.autista = autista;
        this.utente = utente;
        this.veicolo = veicolo;
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.data_partenza = data_partenza;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.ora_partenza = ora_partenza;
        this.prezzo = prezzo;
    }

    public Corsa(Autista autista, Utente utente, Veicolo veicolo, String citta_partenza, String citta_destinazione, Date data_partenza, String inidirizzo_partenza, String indirizzo_destinazione, LocalTime ora_partenza, Integer km_corsa, float prezzo) {
        this.autista = autista;
        this.utente = utente;
        this.veicolo = veicolo;
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.data_partenza = data_partenza;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.ora_partenza = ora_partenza;
        this.km_corsa = km_corsa;
        this.prezzo = prezzo;
    }

    public Corsa(Autista autista, Utente utente, String citta_partenza, String citta_destinazione, Date data_partenza, String inidirizzo_partenza, String indirizzo_destinazione, LocalTime ora_partenza) {
        this.autista = autista;
        this.utente = utente;
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.data_partenza = data_partenza;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.ora_partenza = ora_partenza;
    }

    public Corsa() {
    }

    public Corsa(Autista autista, Utente utente, Veicolo veicolo, String citta_partenza, String citta_destinazione, Date data_partenza, String inidirizzo_partenza, String indirizzo_destinazione, LocalTime ora_partenza) {
        this.autista = autista;
        this.utente = utente;
        this.veicolo = veicolo;
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.data_partenza = data_partenza;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.ora_partenza = ora_partenza;
    }



    public Corsa(Utente utente, String citta_partenza, String citta_destinazione, Date data_partenza, String inidirizzo_partenza, String indirizzo_destinazione, LocalTime ora_partenza) {
        this.utente = utente;
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.data_partenza = data_partenza;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.ora_partenza = ora_partenza;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public String getCitta_partenza() {
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
    }

    public Date getData_partenza() {
        return data_partenza;
    }

    public void setData_partenza(Date data_partenza) {
        this.data_partenza = data_partenza;
    }

    public String getInidirizzo_partenza() {
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
    }

    public LocalTime getOra_partenza() {
        return ora_partenza;
    }

    public void setOra_partenza(LocalTime ora_partenza) {
        this.ora_partenza = ora_partenza;
    }

    public float getPrezzo() {
        getFee();
        return prezzo;
    }

    public Integer getKm_corsa() {
        return km_corsa;
    }

    public void setKm_corsa(Integer km_corsa) {
        this.km_corsa = km_corsa;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    public float getFee(){
        float prezzotot=0;
        if(this.citta_partenza.equals(this.citta_destinazione)) {
            prezzotot=3*km_corsa;
        }
        else {
            prezzotot= (float) (1.5*this.km_corsa);
        }
        prezzotot=getDiscount(prezzotot);
        return prezzotot;
    }


    @Override
    public String toString() {
        return "Corsa{" +
                "autista=" + autista +
                ", utente=" + utente +
                ", veicolo=" + veicolo +
                ", citta_partenza='" + citta_partenza + '\'' +
                ", citta_destinazione='" + citta_destinazione + '\'' +
                ", data_partenza=" + data_partenza +
                ", inidirizzo_partenza='" + inidirizzo_partenza + '\'' +
                ", indirizzo_destinazione='" + indirizzo_destinazione + '\'' +
                ", ora_partenza=" + ora_partenza +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public float getDiscount(float prezzotot) {
        float discount=0;
        float prezzobase = prezzotot;
        //float maggiorazione=0;
        if((this.ora_partenza).isAfter(LocalTime.parse("23:00:00")) && (this.ora_partenza).isBefore(LocalTime.parse("04:59:59"))) {
            discount=-25;
            prezzobase-=prezzobase*(discount/100);

        }
        if(LocalDate.parse(String.valueOf(this.data_partenza)).getDayOfWeek().equals(SATURDAY) || LocalDate.parse(String.valueOf(this.data_partenza)).getDayOfWeek().equals(SUNDAY)) {
            discount=-15;
            prezzobase-=prezzobase*(discount/100);
        }
        if(this.citta_partenza.equals(this.citta_destinazione) != true){
            if(getKm_corsa() >= 100) {
                int km = getKm_corsa()-100;
                discount=15;
                float prezzokm = (float) (km * 1.5*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(this.citta_partenza.equals(this.citta_destinazione)) {
            if(getKm_corsa() >= 10) {
                int km = getKm_corsa()-10;
                discount=20;
                float prezzokm = (float) (km * 3*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(this.veicolo.getN_posti()>4) {
            prezzobase = (float) (prezzobase*1.25+0.25*(this.veicolo.getN_posti()-1));
        }
        if(this.km_corsa<1){
            prezzobase = 2;}
        return prezzobase;
    }
}
