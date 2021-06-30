package com.jaksa.it355.form;

import com.jaksa.it355.entity.NarudzbeniceEntity;
import com.jaksa.it355.entity.ProizvodiEntity;

import java.util.ArrayList;

public class KupacForm {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private ArrayList<ProizvodiEntity> proizvodi;
    private NarudzbeniceEntity narudzbenica;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ProizvodiEntity> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(ArrayList<ProizvodiEntity> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public NarudzbeniceEntity getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(NarudzbeniceEntity narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    @Override
    public String toString() {
        return "KupacForm{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
