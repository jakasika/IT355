package com.jaksa.it355.entity;

import javax.persistence.*;

@Entity
@Table(name = "kategorije", schema = "jaksa_se211", catalog = "")
public class KategorijeEntity {
    private int id;
    private String naziv;

    public KategorijeEntity(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public KategorijeEntity(String naziv) {
        this.naziv = naziv;
    }

    public KategorijeEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KategorijeEntity that = (KategorijeEntity) o;

        if (id != that.id) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        return result;
    }
}
