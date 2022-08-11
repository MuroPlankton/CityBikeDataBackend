package com.miroplanting.CityBikeDataBackend.trip;

import com.miroplanting.CityBikeDataBackend.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip/")
public class TripRestController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public Page<Trip> getTrips(Pageable pageable) {
        return tripRepository.findAll(pageable);
    }

    @PostMapping(params = {"departure_station", "return_station"})
    public Trip addTrip(@RequestParam int departure_station, @RequestParam int return_station,@RequestBody Trip trip) {
        trip.setDepartureStation(stationRepository.findById(departure_station).get());
        trip.setReturnStation(stationRepository.findById(return_station).get());
        return tripRepository.save(trip);
    }
}
