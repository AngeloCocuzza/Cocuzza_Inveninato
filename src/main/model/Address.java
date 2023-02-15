package model;

import java.time.LocalTime;
import java.util.Date;

public class Address {
    private String citta_partenza;
    private String citta_destinazione;
    private String inidirizzo_partenza;
    private String indirizzo_destinazione;

    private Integer km_corsa;

    public Address(String citta_partenza, String citta_destinazione, String inidirizzo_partenza, String indirizzo_destinazione, Integer km_corsa) {
        this.citta_partenza = citta_partenza;
        this.citta_destinazione = citta_destinazione;
        this.inidirizzo_partenza = inidirizzo_partenza;
        this.indirizzo_destinazione = indirizzo_destinazione;
        this.km_corsa = km_corsa;
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


    public Integer getKm_corsa() {
        return km_corsa;
    }

    public void setKm_corsa(Integer km_corsa) {
        this.km_corsa = km_corsa;
    }

    @Override
    public String toString() {
        return "Address{" +
                "citta_partenza='" + citta_partenza + '\'' +
                ", citta_destinazione='" + citta_destinazione + '\'' +
                ", inidirizzo_partenza='" + inidirizzo_partenza + '\'' +
                ", indirizzo_destinazione='" + indirizzo_destinazione + '\'' +
                ", km_corsa=" + km_corsa +
                '}';
    }
}
