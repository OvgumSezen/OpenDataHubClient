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

    public MobilityEntity getStations(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(baseUrl + "/" + mobilityType.getId()))
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);
        return mapToMobilityEntity(response);
    }

    public MobilityEntity getStationsAndDatatypes(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(baseUrl + "/" + mobilityType.getId() + DATATYPES_ENDPOINT))
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);
        return mapToMobilityEntity(response);
    }

    public MobilityEntity getStationsAndDatatypesAndMeasurement(MobilityType mobilityType) {
        deserializationStrategy = new MobilityEntityDeserializationStrategy(mobilityType);
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(baseUrl + "/" + mobilityType.getId() + DATATYPES_ENDPOINT + MEASUREMENT_ENDPOINT))
                .GET()
                .build();

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

}
