package com.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "Bills", schema = EntitiesUtilities.DATABASE_SCHEMA)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill", nullable = false)
    private int id;
    private String number;
    private int amount;
    private Timestamp date; // Data Emissione
    private Timestamp creationDate;
    private Timestamp cancellationDate;
    private User user;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Basic
    @Column
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
    public Timestamp getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Timestamp cancellationDate) {
        this.cancellationDate = cancellationDate;
    }


    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                amount == bill.amount &&
                Objects.equals(number, bill.number) &&
                Objects.equals(date, bill.date) &&
                Objects.equals(creationDate, bill.creationDate) &&
                Objects.equals(cancellationDate, bill.cancellationDate) &&
                Objects.equals(user, bill.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, amount, date, creationDate, cancellationDate, user);
    }
}
