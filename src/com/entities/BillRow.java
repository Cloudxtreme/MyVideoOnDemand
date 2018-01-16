package com.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bill_rows", schema = EntitiesUtilities.DATABASE_SCHEMA)
public class BillRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_row", nullable = false)
    private int id;
    private Film film;
    private double price;
    private Bill bill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Basic
    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @ManyToOne
    @JoinColumn(name = "id_bill", nullable = false)
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillRow billRow = (BillRow) o;
        return id == billRow.id &&
                Double.compare(billRow.price, price) == 0 &&
                Objects.equals(film, billRow.film) &&
                Objects.equals(bill, billRow.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, price, bill);
    }
}

