package com.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Managers", schema = EntitiesUtilities.DATABASE_SCHEMA)
public class Manager {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    @Column(name = "id", nullable = false)private int id;
    private String heading;
    private String businessName;
    private int iva;
    private int phoneNumber;
    private int cap;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column
    public String getHeading() {
        return heading;
    }

    @Basic
    @Column
    public String getBusinessName() {
        return businessName;
    }

    @Basic
    @Column
    public int getIva() {
        return iva;
    }

    @Basic
    @Column
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Basic
    @Column
    public int getCap() {
        return cap;
    }

    @Basic
    @Column
    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    @Basic
    @Column
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return id == manager.id &&
                iva == manager.iva &&
                phoneNumber == manager.phoneNumber &&
                cap == manager.cap &&
                Objects.equals(heading, manager.heading) &&
                Objects.equals(businessName, manager.businessName) &&
                Objects.equals(creationDate, manager.creationDate) &&
                Objects.equals(lastUpdateDate, manager.lastUpdateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, heading, businessName, iva, phoneNumber, cap, creationDate, lastUpdateDate);
    }
}
