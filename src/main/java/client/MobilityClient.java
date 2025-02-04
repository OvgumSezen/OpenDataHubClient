package client;

import config.ODHClientConfig;
import dao.mobility.MobilityEntity;
import dao.mobility.MobilityType;
import deserializer.ODHEntityDeserializationStrategy;
import deserializer.impl.MobilityEntityDeserializationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MobilityClient {
    private static MobilityClient instance;

    private static final Logger logger = LoggerFactory.getLogger(MobilityClient.class);

    private final String DATATYPES_ENDPOINT = "/*";
    private final String MEASUREMENT_ENDPOINT = "/latest";

    private final String baseUrl;
    private final HttpClient httpClient;
    private ODHEntityDeserializationStrategy deserializationStrategy;

    private String uriString;

    private MobilityClient() {
        baseUrl = ODHClientConfig.getInstance().getBaseUrl();
        httpClient = HttpClient.newHttpClient();
    }

    public static MobilityClient getInstance() {
        if (instance == null) {
            instance = new MobilityClient();
        }

        return instance;
    }

    public MobilityClient getStations(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);

        if(uriString.isBlank()) {
            uriString = baseUrl + "/" + mobilityType.getId() + "?";
        } else {
            logURIAlreadySet();
        }

        return this;
    }

    public MobilityClient getStationsAndDatatypes(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);

        if(uriString.isBlank()) {
            uriString = baseUrl + "/" + mobilityType.getId() + DATATYPES_ENDPOINT + "?";
        } else {
            logURIAlreadySet();
        }

        return this;
    }

    public MobilityClient getStationsAndDatatypesAndMeasurement(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);

        if(uriString.isBlank()) {
            uriString = baseUrl + "/" + mobilityType.getId() + DATATYPES_ENDPOINT + MEASUREMENT_ENDPOINT + "?";
        } else {
            logURIAlreadySet();
        }

        return this;
    }

    public MobilityClient setLimit(Integer limit) {
        if(!uriString.isBlank() && uriString.contains("limit")) {
            logURIAlreadySet();
            return this;
        }

        uriString += "limit=" + limit + "&";
        return this;
    }

    public MobilityClient setOffset(Integer offset) {
        if(!uriString.isBlank() && uriString.contains("offset")) {
            logURIAlreadySet();
            return this;
        }

        uriString += "offset=" + offset + "&";
        return this;
    }

    public MobilityEntity execute() {
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(uriString))
                .GET()
                .build();
        uriString = "";

        HttpResponse<String> response = sendRequest(request);
        return mapToMobilityEntity(response);
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        try{
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("LOG: Response received: {}", response.body());
            return response;
        } catch (IOException | InterruptedException e) {
            logger.error("ERROR: send request error: {} ", e.getMessage());
            return null;
        }
    }

    private MobilityEntity mapToMobilityEntity(HttpResponse<String> response) {
        if(response == null) {
            return null;
        }

        MobilityEntity mobilityEntity = (MobilityEntity) deserializationStrategy.deserialize(response);
        logMobilityEntity(mobilityEntity);

        return mobilityEntity;
    }

    private static void logMobilityEntity(MobilityEntity mobilityEntity) {
        if(mobilityEntity == null) {
            logger.info("LOG: MobilityEntity not found: null");
        } else {
            logger.info("LOG: Mobility entity created: {}", mobilityEntity);
        }
    }

    private void logURIAlreadySet() {
        logger.info("LOG: url is already set: {}", uriString );
    }
}
