package com.miroplanting.CityBikeDataBackend.csv;

import com.miroplanting.CityBikeDataBackend.station.StationRepository;
import com.miroplanting.CityBikeDataBackend.trip.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class CSVImportService {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TripRepository tripRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void importDataFromCSVs() {
        stationRepository.saveAll(StationImporter.ImportStationsFromCSV());

        Timer timer = new Timer();
        timer.schedule(tripImportTask, 10000);

    }

    private TimerTask tripImportTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println(System.getProperty("user.dir"));
            File csvFileFolder = new File(System.getProperty("user.dir") + "\\csvFiles");
            File[] csvFilesInFolder = csvFileFolder.listFiles();

            for (File file : csvFilesInFolder) {
                if (TripImporter.doesCSVContainTrips(file)) {
                    tripRepository.saveAll(TripImporter.importTripsFromCSV(file, stationRepository));
                }
            }
        }
    };
}
