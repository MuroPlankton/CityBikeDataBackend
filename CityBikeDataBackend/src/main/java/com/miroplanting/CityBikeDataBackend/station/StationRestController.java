package com.miroplanting.CityBikeDataBackend.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station/")
public class StationRestController {

    @Autowired
    private StationRepository stationRepository;

    @GetMapping
    public Page<Station> getStations(Pageable pageable) {
        return stationRepository.findAll(pageable);
    }

    @PostMapping
    public Station addStation(@RequestBody Station station) {
        return stationRepository.save(station);
    }

    //TODO: list stations preferably with pagination
    //search functionality would be cool

    //TODO: get single station. probs based on ID
    //would be cool to get total number of trips to and from station
    //and avg trip lengths to and from station
    //and most popular return stations of trips started from station and departure station of trips ending at this station
    //and ability to filter these stats by months or something

    //TODO: add station
    //TODO: modify station
    //TODO: delete station
}
