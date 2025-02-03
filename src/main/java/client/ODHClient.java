package client;

import dao.ODHEntity;
import dao.mobility.MobilityType;


public class ODHClient {
    private static ODHClient instance;

    private final MobilityClient mobilityClient;


    private ODHClient() {
        this.mobilityClient = MobilityClient.getInstance();
    }

    public static ODHClient getInstance() {
        if (instance == null) {
            instance = new ODHClient();
        }

        return instance;
    }

    public ODHEntity getStations(MobilityType mobilityType) {
        return mobilityClient.getStations(mobilityType);
    }

    public ODHEntity getStationsAndDatatypes(MobilityType mobilityType) {
        return mobilityClient.getStationsAndDatatypes(mobilityType);
    }

    public ODHEntity getStationsAndDatatypesAndMeasurement(MobilityType mobilityType) {
        return mobilityClient.getStationsAndDatatypesAndMeasurement(mobilityType);
    }
}
