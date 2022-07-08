package com.miroplanting.CityBikeDataBackend.station;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends PagingAndSortingRepository<Station, Integer> {
}
