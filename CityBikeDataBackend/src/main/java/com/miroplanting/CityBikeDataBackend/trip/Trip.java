package com.miroplanting.CityBikeDataBackend.trip;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miroplanting.CityBikeDataBackend.station.Station;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private String id;

    private Time departureTime, returnTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_station_id", nullable = false)
    @JsonIgnore
    private Station departureStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "return_station_id", nullable = false)
    @JsonIgnore
    private Station returnStation;

    int distanceMeters;

    public Trip() {
    }

    public Trip(Time departureTime, Time returnTime, Station departureStation, Station returnStation, int distanceMeters) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.distanceMeters = distanceMeters;
    }

    public Station getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(Station returnStation) {
        this.returnStation = returnStation;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
        this.returnTime = returnTime;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }
}
