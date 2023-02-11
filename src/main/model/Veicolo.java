package model;

import java.util.Date;

public class Veicolo {
    private String targa;
    private String autista;


    private String marca;
    private String modello;
    private String colore;
    private int n_posti;

    public Veicolo(String targa, String autista, String marca, String modello, String colore, int n_posti) {
        this.targa = targa;
        this.autista = autista;
        this.marca = marca;
        this.modello = modello;
        this.colore = colore;
        this.n_posti = n_posti;
    }

    public String getTarga() {
        return targa;
    }

    public String getAutista() {
        return autista;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public String getColore() {
        return colore;
    }

    public int getN_posti() {
        return n_posti;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public void setAutista(String autista) {
        this.autista = autista;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public void setN_posti(int n_posti) {
        this.n_posti = n_posti;
    }
}
