package model;

import java.time.LocalTime;
import java.util.Date;

public class Disponibilita {

    Autista autista;
    Date giorno_disponibilita;
    LocalTime ora_inizio;
    LocalTime ora_fine;

    String citta_partenza;

    public Disponibilita(Autista autista, Date giorno_disponibilita, LocalTime ora_inizio, LocalTime ora_fine, String citta_partenza) {
        this.autista = autista;
        this.giorno_disponibilita = giorno_disponibilita;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
        this.citta_partenza = citta_partenza;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public Date getGiorno_disponibilita() {
        return giorno_disponibilita;
    }

    public void setGiorno_disponibilita(Date giorno_disponibilita) {
        this.giorno_disponibilita = giorno_disponibilita;
    }

    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(LocalTime ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public LocalTime getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(LocalTime ora_fine) {
        this.ora_fine = ora_fine;
    }

    public String getCitta_partenza() {
        return citta_partenza;
    }

    public void setCitta_partenza(String citta_partenza) {
        this.citta_partenza = citta_partenza;
    }

    @Override
    public String toString() {
        return "Disponibilita{" +
                "autista='" + autista + '\'' +
                ", giorno_disponibilita=" + giorno_disponibilita +
                ", ora_inizio=" + ora_inizio +
                ", ora_fine=" + ora_fine +
                ", citta_partenza='" + citta_partenza + '\'' +
                '}';
    }
}
