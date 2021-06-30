package com.jaksa.it355.form;

import java.math.BigDecimal;

public class NaruceniProizvodiForm {
    private int id;
    private BigDecimal cenaProizvoda;
    private int kolicina;
    private int narudzbenica_id;
    private int proizvod_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCenaProizvoda() {
        return cenaProizvoda;
    }

    public void setCenaProizvoda(BigDecimal cenaProizvoda) {
        this.cenaProizvoda = cenaProizvoda;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getNarudzbenica_id() {
        return narudzbenica_id;
    }

    public void setNarudzbenica_id(int narudzbenica_id) {
        this.narudzbenica_id = narudzbenica_id;
    }

    public int getProizvod_id() {
        return proizvod_id;
    }

    public void setProizvod_id(int proizvod_id) {
        this.proizvod_id = proizvod_id;
    }

    @Override
    public String toString() {
        return "NaruceniProizvodiForm{" +
                "id=" + id +
                ", cenaProizvoda=" + cenaProizvoda +
                ", kolicina=" + kolicina +
                ", narudzbenica_id=" + narudzbenica_id +
                ", proizvod_id=" + proizvod_id +
                '}';
    }
}
