package com.jaksa.it355.form;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class NarudzbenicaForm {
    private int id;
    private Timestamp vreme;
    private BigDecimal ukupnaCena;
    private int kupac_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getVreme() {
        return vreme;
    }

    public void setVreme(Timestamp vreme) {
        this.vreme = vreme;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public int getKupac_id() {
        return kupac_id;
    }

    public void setKupac_id(int kupac_id) {
        this.kupac_id = kupac_id;
    }

    @Override
    public String toString() {
        return "NarudzbenicaForm{" +
                "id=" + id +
                ", vreme=" + vreme +
                ", ukupnaCena=" + ukupnaCena +
                ", kupac_id=" + kupac_id +
                '}';
    }
}
