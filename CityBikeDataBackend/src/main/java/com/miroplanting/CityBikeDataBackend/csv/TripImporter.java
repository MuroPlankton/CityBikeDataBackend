package com.miroplanting.CityBikeDataBackend.csv;

import com.miroplanting.CityBikeDataBackend.station.Station;
import com.miroplanting.CityBikeDataBackend.station.StationRepository;
import com.miroplanting.CityBikeDataBackend.trip.Trip;
import com.miroplanting.CityBikeDataBackend.trip.TripRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class TripImporter {

    public static boolean doesCSVContainTrips(File csvFile) {
        try {
            Scanner scanner = new Scanner(csvFile);
            String tripFirstLine = "Departure,Return,Departure station id,Departure station name,Return station id,Return station name,Covered distance (m),Duration (sec.)";
            return scanner.nextLine().contains(tripFirstLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Trip> importTripsFromCSV(File csvFile, StationRepository stationRepository) {
        List<Trip> trips = new ArrayList<>();

        try {
            FileReader tripFileReader = new FileReader(csvFile);
            CSVParser tripCSVParser = new CSVParser(tripFileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord tripRecord : tripCSVParser) {
                int distance = 0;
                String distanceString = tripRecord.get("Covered distance (m)");
                try {
                    distance = Integer.parseInt(distanceString);
                } catch (NumberFormatException e) {
                    distance = (int) Double.parseDouble(distanceString);
                }
                int duration = Integer.parseInt(tripRecord.get("Duration (sec.)"));

                if (distance > 10 && duration > 10) {
                    Trip trip = new Trip();
                    trip.setDepartureTime(getTimeFromString(tripRecord.get(0)));
                    trip.setReturnTime(getTimeFromString(tripRecord.get("Return")));

                    Station departureStation, returnStation;
                    try {
                        departureStation = (stationRepository.findById(Integer.parseInt(tripRecord.get("Departure station id"))).get());
                        returnStation = (stationRepository.findById(Integer.parseInt(tripRecord.get("Return station id"))).get());
                    } catch (NoSuchElementException e) {
                        break;
                    }

                    if (departureStation != null && returnStation != null) {
                        trip.setDepartureStation(departureStation);
                        trip.setReturnStation(returnStation);
                        trip.setDistanceMeters(distance);
                        trip.setDuration(duration);
                        trips.add(trip);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trips;
    }

    private static LocalDateTime getTimeFromString(String timeFromCSV) {
        String[] dateAndTime = timeFromCSV.split("T");
        String[] yearMonthDay = dateAndTime[0].split("-");
        String[] hourMinuteSecond = dateAndTime[1].split(":");

        return LocalDateTime.of(Integer.parseInt(yearMonthDay[0]), Integer.parseInt(yearMonthDay[1]),
                Integer.parseInt(yearMonthDay[2]), Integer.parseInt(hourMinuteSecond[0]),
                Integer.parseInt(hourMinuteSecond[1]), Integer.parseInt(hourMinuteSecond[2]));
    }
}
