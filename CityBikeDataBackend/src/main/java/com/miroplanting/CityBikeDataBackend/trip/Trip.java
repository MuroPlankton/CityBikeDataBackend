package com.miroplanting.CityBikeDataBackend.trip;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miroplanting.CityBikeDataBackend.station.Station;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "return_time")
    private LocalDateTime returnTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_station_id", nullable = false)
    @JsonIgnore
    private Station departureStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "return_station_id", nullable = false)
    @JsonIgnore
    private Station returnStation;

    @Column(name = "distance_meters")
    private int distanceMeters;

    private int duration;

    public Trip() {
    }

    public Trip(LocalDateTime departureTime, LocalDateTime returnTime, Station departureStation, Station returnStation, int distanceMeters, int duration) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.distanceMeters = distanceMeters;
        this.duration = duration;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
