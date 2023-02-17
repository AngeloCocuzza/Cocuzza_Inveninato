package model;

public class Veicolo {
    private String targa;
    private String autista;
    private String marca;
    private String modello;
    private String colore;
    private Integer n_posti;

    public Veicolo(String targa, String autista, String marca, String modello, String colore, Integer n_posti) {
        this.targa = targa;
        this.autista = autista;
        this.marca = marca;
        this.modello = modello;
        this.colore = colore;
        this.n_posti = n_posti;
    }

    public Veicolo() {
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

    public Integer getN_posti() {
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

    public void setN_posti(Integer n_posti) {
        this.n_posti = n_posti;
    }

    @Override
    public String toString() {
        return "Veicolo{" +
                "targa='" + targa + '\'' +
                ", autista='" + autista + '\'' +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", colore='" + colore + '\'' +
                ", n_posti=" + n_posti +
                '}';
    }
}
