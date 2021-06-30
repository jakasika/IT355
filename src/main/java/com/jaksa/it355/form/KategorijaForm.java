package com.jaksa.it355.form;

public class KategorijaForm {
    private int id;
    private String naziv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "KategorijaForm{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
