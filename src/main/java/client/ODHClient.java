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

    public ODHClient getStations(MobilityType mobilityType) {
        mobilityClient.getStations(mobilityType);
        return this;
    }

    public ODHClient getStationsAndDatatypes(MobilityType mobilityType) {
        mobilityClient.getStationsAndDatatypes(mobilityType);
        return this;
    }

    public ODHClient getStationsAndDatatypesAndMeasurement(MobilityType mobilityType) {
        mobilityClient.getStationsAndDatatypesAndMeasurement(mobilityType);
        return this;
    }

    public ODHClient setLimit(Integer limit) {
        mobilityClient.setLimit(limit);
        return this;
    }

    public ODHClient setOffset(Integer offset) {
        mobilityClient.setOffset(offset);
        return this;
    }

    public ODHEntity execute() {
        return mobilityClient.execute();
    }
}
