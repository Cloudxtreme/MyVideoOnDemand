package com.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "films", schema = EntitiesUtilities.DATABASE_SCHEMA)
@NamedQuery(name = "Films.findAll", query = "SELECT f from Film f")
public class Film implements Comparable<Film> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_film", nullable = false)
    private int id;
    private String title;
    private int year;
    private String director;
    private String cast;
    private Integer duration;
    private String description;
    private String fileCoverName;
    private Timestamp creationDate;
    private Timestamp lastUpdateDate;
    private Genre filmGenre;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column
    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Basic
    @Column
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column
    public String getFileCoverName() {
        return fileCoverName;
    }

    public void setFileCoverName(String fileCoverName) {
        this.fileCoverName = fileCoverName;
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
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film that = (Film) o;

        if (id != that.id) return false;
        if (year != that.year) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (cast != null ? !cast.equals(that.cast) : that.cast != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        return lastUpdateDate != null ? lastUpdateDate.equals(that.lastUpdateDate) : that.lastUpdateDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (cast != null ? cast.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_genre", nullable = false)
    public Genre getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(Genre genre) {
        this.filmGenre = genre;
    }

    public int compareTo(Film film) {
        return this.title.compareTo(film.title);
    }
}
