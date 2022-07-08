package com.miroplanting.CityBikeDataBackend.trip;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends PagingAndSortingRepository<Trip, String> {
}
