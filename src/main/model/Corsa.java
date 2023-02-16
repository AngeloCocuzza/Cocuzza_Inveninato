package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class Corsa implements Discount {
    private Autista autista;
    private Utente utente;
    private Veicolo veicolo;
    //private String citta_partenza;
    //private String citta_destinazione;
    private Date data_partenza;
    //private String inidirizzo_partenza;
    //private String indirizzo_destinazione;
    private LocalTime ora_partenza;

    //private Integer km_corsa;

    private Address address;
    private float prezzo;

    public Corsa(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo) {
        this.autista = autista;
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
        this.prezzo = prezzo;
    }


    public Corsa(Autista autista, Utente utente, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo) {
        this.autista = autista;
        this.utente = utente;
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
        this.prezzo = prezzo;
    }

    public Corsa(Autista autista, Date data_partenza, LocalTime ora_partenza, Address address) {
        this.autista = autista;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
    }

    public Corsa(Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address) {
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
    }

    public Corsa() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Date getData_partenza() {
        return data_partenza;
    }

    public void setData_partenza(Date data_partenza) {
        this.data_partenza = data_partenza;
    }

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

    public LocalTime getOra_partenza() {
        return ora_partenza;
    }

    public void setOra_partenza(LocalTime ora_partenza) {
        this.ora_partenza = ora_partenza;
    }

    public float getPrezzo() {
        return prezzo;
    }

    /*public Integer getKm_corsa() {
        return km_corsa;
    }

    public void setKm_corsa(Integer km_corsa) {
        this.km_corsa = km_corsa;
    }*/

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    public float getFee(){
        float prezzotot=0;
        if(this.address.getCitta_partenza().equals(this.address.getCitta_destinazione())) {
            prezzotot=3*this.address.getKm_corsa();
        }
        else {
            prezzotot= (float) (1.5*this.address.getKm_corsa());
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
                ", data_partenza=" + data_partenza +
                ", ora_partenza=" + ora_partenza +
                ", address=" + address +
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
        if(this.address.getCitta_partenza().equals(this.address.getCitta_destinazione()) != true){
            if(this.address.getKm_corsa() >= 100) {
                int km = this.address.getKm_corsa()-100;
                discount=15;
                float prezzokm = (float) (km * 1.5*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(this.address.getCitta_partenza().equals(this.address.getCitta_destinazione())) {
            if(this.address.getKm_corsa() >= 10) {
                int km = this.address.getKm_corsa()-10;
                discount=20;
                float prezzokm = (float) (km * 3*(discount/100));
                prezzobase=prezzobase-prezzokm;
            }
        }
        if(this.veicolo.getN_posti()>4) {
            prezzobase = (float) (prezzobase*(0.25*(this.veicolo.getN_posti())));
        }
        if(this.address.getKm_corsa()<1){
            prezzobase = 2;}
        return prezzobase;
    }
}
