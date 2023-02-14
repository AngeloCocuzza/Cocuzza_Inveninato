package model;

import java.time.LocalTime;
import java.util.Date;

public class Corsa {
    private Autista autista;
    private Utente utente;
    private Veicolo veicolo;
    private String citta_partenza;
    private String citta_destinazione;
    private Date data_partenza;
    private String inidirizzo_partenza;
    private String indirizzo_destinazione;
    private LocalTime ora_partenza;
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
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
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
}
