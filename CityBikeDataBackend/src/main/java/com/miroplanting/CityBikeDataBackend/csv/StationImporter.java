package com.miroplanting.CityBikeDataBackend.csv;

import com.miroplanting.CityBikeDataBackend.station.Station;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StationImporter {

    public static List<Station> ImportStationsFromCSV() {
        File stationCSVFile = new File(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\miroplanting\\CityBikeDataBackend\\csv\\files" +
                "\\Helsingin_ja_Espoon_kaupunkipyöräasemat_avoin.csv");

        List<Station> stations = new ArrayList<>();
        try {
            FileReader stationFileReader = new FileReader(stationCSVFile);
            CSVParser stationParser = CSVParser.parse(stationFileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord stationRecord : stationParser) {
                Station station = new Station();
                station.setId(Integer.parseInt(stationRecord.get("ID")));
                station.setNimi(stationRecord.get("Nimi"));
                station.setNamn(stationRecord.get("Namn"));
                station.setOsoite(stationRecord.get("Osoite"));
                station.setAdress(stationRecord.get("Adress"));

                String kaupunki = stationRecord.get("Kaupunki");
                station.setKaupunki(kaupunki.isBlank() ? "Helsinki" : kaupunki);

                String stad = stationRecord.get("Stad");
                station.setStad(stad.isBlank() ? "Helsingfors" : stad);

                station.setOperator(stationRecord.get("Operaattor"));
                station.setCapacity(Integer.parseInt(stationRecord.get("Kapasiteet")));
                station.setX(Double.parseDouble(stationRecord.get("x")));
                station.setY(Double.parseDouble(stationRecord.get("y")));

                stations.add(station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }
}
