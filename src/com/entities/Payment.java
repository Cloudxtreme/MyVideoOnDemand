package com.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "payments", schema = EntitiesUtilities.DATABASE_SCHEMA)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment", nullable = false)
    private int id;
    private Timestamp date;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
    private Bill bill;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column
    public Timestamp getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Timestamp lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @OneToOne
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
        Payment payment = (Payment) o;
        return id == payment.id &&
                Objects.equals(date, payment.date) &&
                Objects.equals(creationDate, payment.creationDate) &&
                Objects.equals(lastEditDate, payment.lastEditDate) &&
                Objects.equals(bill, payment.bill);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, creationDate, lastEditDate, bill);
    }
}
