package com.jaksa.it355.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "proizvodi", schema = "jaksa_se211", catalog = "")
public class ProizvodiEntity {
    private int id;
    private String naziv;
    private String opis;
    private BigDecimal cena;
    private KategorijeEntity kategorija;

    public ProizvodiEntity() {
    }

    public ProizvodiEntity(int id, String naziv, String opis, BigDecimal cena, KategorijeEntity kategorija) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.kategorija = kategorija;
    }

    public ProizvodiEntity(String naziv, String opis, BigDecimal cena, KategorijeEntity kategorija) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.kategorija = kategorija;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "naziv", nullable = false, length = 255)
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Basic
    @Column(name = "opis", nullable = false, length = 255)
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "cena", nullable = false, precision = 2)
    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kategorija_id", nullable = false)
    public KategorijeEntity getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijeEntity kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProizvodiEntity that = (ProizvodiEntity) o;

        if (id != that.id) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (opis != null ? !opis.equals(that.opis) : that.opis != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "ProizvodiEntity{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                '}';
    }
}
