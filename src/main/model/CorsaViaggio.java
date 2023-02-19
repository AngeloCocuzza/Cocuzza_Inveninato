package model;

import java.time.LocalTime;
import java.util.Date;

public abstract class CorsaViaggio {
    private Integer ID;
    private Autista autista;
    private Veicolo veicolo;
    private Date data_partenza;
    private LocalTime ora_partenza;
    private Address address;
    private float prezzo;
    private Recensione recensione;

    public CorsaViaggio(Integer ID, Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo) {
        this.ID = ID;
        this.autista = autista;
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
        this.prezzo = prezzo;
    }

    public CorsaViaggio() {
    }

    public CorsaViaggio(Autista autista, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address, float prezzo) {
        this.autista = autista;
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
        this.prezzo = prezzo;
    }

    public CorsaViaggio(Autista autista, Date data_partenza, LocalTime ora_partenza, Address address) {
        this.autista = autista;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
    }

    public CorsaViaggio(Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address) {
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
    }

    public CorsaViaggio(Integer ID, Veicolo veicolo, Date data_partenza, LocalTime ora_partenza, Address address) {
        this.ID = ID;
        this.veicolo = veicolo;
        this.data_partenza = data_partenza;
        this.ora_partenza = ora_partenza;
        this.address = address;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Date getData_partenza() {
        return data_partenza;
    }

    public void setData_partenza(Date data_partenza) {
        this.data_partenza = data_partenza;
    }

    public LocalTime getOra_partenza() {
        return ora_partenza;
    }

    public void setOra_partenza(LocalTime ora_partenza) {
        this.ora_partenza = ora_partenza;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }

    @Override
    public String toString() {
        return "CorsaViaggio{" +
                "ID=" + ID +
                ", autista=" + autista +
                ", veicolo=" + veicolo +
                ", data_partenza=" + data_partenza +
                ", ora_partenza=" + ora_partenza +
                ", address=" + address +
                ", prezzo=" + prezzo +
                ", recensione=" + recensione +
                '}';
    }

    public abstract void setPrezzo();

}
