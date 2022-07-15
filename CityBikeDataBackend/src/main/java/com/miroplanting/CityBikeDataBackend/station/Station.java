package com.miroplanting.CityBikeDataBackend.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miroplanting.CityBikeDataBackend.trip.Trip;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    //name, address and city in Finnish
    private String nimi, osoite, kaupunki;

    //name, address and city in Swedish
    private String namn, adress, stad;

    private String operator;
    private int capacity;
    private double x, y;

    @OneToMany(mappedBy = "departureStation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trip> tripsDeparted;

    @OneToMany(mappedBy = "returnStation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trip> tripsReturned;

    public Station() {
    }

    public Station(int id, String nimi, String osoite, String kaupunki, String namn, String adress, String stad,
                   String operator, int capacity, double x, double y) {
        this.id = id;
        this.nimi = nimi;
        this.osoite = osoite;
        this.kaupunki = kaupunki;
        this.namn = namn;
        this.adress = adress;
        this.stad = stad;
        this.operator = operator;
        this.capacity = capacity;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
