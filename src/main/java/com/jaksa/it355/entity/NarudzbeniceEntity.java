package com.jaksa.it355.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "narudzbenice", schema = "jaksa_se211", catalog = "")
public class NarudzbeniceEntity {
    private int id;
    private Timestamp vreme;
    private BigDecimal ukupnaCena;
    private KupacEntity kupac;

    public NarudzbeniceEntity(Timestamp vreme, BigDecimal ukupnaCena, KupacEntity kupac) {
        this.vreme = vreme;
        this.ukupnaCena = ukupnaCena;
        this.kupac = kupac;
    }

    public NarudzbeniceEntity() {
    }

    public NarudzbeniceEntity(Timestamp vreme) {
        this.vreme = vreme;
    }

    public NarudzbeniceEntity(int id, Timestamp vreme, BigDecimal ukupnaCena, KupacEntity kupac) {
        this.id = id;
        this.vreme = vreme;
        this.ukupnaCena = ukupnaCena;
        this.kupac = kupac;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vreme", nullable = false)
    public Timestamp getVreme() {
        return vreme;
    }

    public void setVreme(Timestamp vreme) {
        this.vreme = vreme;
    }

    @Basic
    @Column(name = "ukupna_cena", nullable = true, precision = 2)
    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupac_id", nullable = true)
    public KupacEntity getKupac() {
        return kupac;
    }

    public void setKupac(KupacEntity kupac) {
        this.kupac = kupac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NarudzbeniceEntity that = (NarudzbeniceEntity) o;

        if (id != that.id) return false;
        if (vreme != null ? !vreme.equals(that.vreme) : that.vreme != null) return false;
        if (ukupnaCena != null ? !ukupnaCena.equals(that.ukupnaCena) : that.ukupnaCena != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vreme != null ? vreme.hashCode() : 0);
        result = 31 * result + (ukupnaCena != null ? ukupnaCena.hashCode() : 0);
        return result;
    }
}
