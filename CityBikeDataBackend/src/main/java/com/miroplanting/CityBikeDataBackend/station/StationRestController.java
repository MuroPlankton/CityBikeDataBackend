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
}
