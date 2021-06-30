package com.jaksa.it355.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "naruceni_proizvodi", schema = "jaksa_se211", catalog = "")
public class NaruceniProizvodiEntity {
    private int id;
    private BigDecimal cenaProizvoda;
    private int kolicina;
    private NarudzbeniceEntity narudzbenica;
    private ProizvodiEntity proizvod;

    public NaruceniProizvodiEntity(BigDecimal cenaProizvoda, int kolicina, NarudzbeniceEntity narudzbenica, ProizvodiEntity proizvod) {
        this.cenaProizvoda = cenaProizvoda;
        this.kolicina = kolicina;
        this.narudzbenica = narudzbenica;
        this.proizvod = proizvod;
    }

    public NaruceniProizvodiEntity(int id, BigDecimal cenaProizvoda, int kolicina, NarudzbeniceEntity narudzbenica, ProizvodiEntity proizvod) {
        this.id = id;
        this.cenaProizvoda = cenaProizvoda;
        this.kolicina = kolicina;
        this.narudzbenica = narudzbenica;
        this.proizvod = proizvod;
    }

    public NaruceniProizvodiEntity() {
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
    @Column(name = "cena_proizvoda", nullable = false, precision = 2)
    public BigDecimal getCenaProizvoda() {
        return cenaProizvoda;
    }

    public void setCenaProizvoda(BigDecimal cenaProizvoda) {
        this.cenaProizvoda = cenaProizvoda;
    }

    @Basic
    @Column(name = "kolicina", nullable = false)
    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "narudzbenica_id", nullable = false)
    public NarudzbeniceEntity getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(NarudzbeniceEntity narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proizvod_id", nullable = false)
    public ProizvodiEntity getProizvod() {
        return proizvod;
    }

    public void setProizvod(ProizvodiEntity proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NaruceniProizvodiEntity that = (NaruceniProizvodiEntity) o;

        if (id != that.id) return false;
        if (kolicina != that.kolicina) return false;
        if (cenaProizvoda != null ? !cenaProizvoda.equals(that.cenaProizvoda) : that.cenaProizvoda != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cenaProizvoda != null ? cenaProizvoda.hashCode() : 0);
        result = 31 * result + kolicina;
        return result;
    }
}
