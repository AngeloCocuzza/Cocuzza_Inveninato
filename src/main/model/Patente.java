package model;

import java.util.Date;

public class Patente {
    private String codice;
    private Autista autista;

    private Date data_conseguimento;
    private Date data_scadenza;
    private String livello;

    public Patente(String codice, Autista autista, Date data_conseguimento, Date data_scadenza, String livello) {
        this.codice = codice;
        this.autista = autista;
        this.data_conseguimento = data_conseguimento;
        this.data_scadenza = data_scadenza;
        this.livello = livello;
    }

    public String getCodice() {
        return codice;
    }

    public Autista getAutista() {
        return autista;
    }

    public Date getData_conseguimento() {
        return data_conseguimento;
    }

    public Date getData_scadenza() {
        return data_scadenza;
    }

    public String getLivello() {
        return livello;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public void setData_conseguimento(Date data_conseguimento) {
        this.data_conseguimento = data_conseguimento;
    }

    public void setData_scadenza(Date data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }
}

