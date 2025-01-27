import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ODHClientConfig;
import dao.MobilityEntity;
import dao.MobilityType;
import deserializer.ObjectMapperFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//TODO: Add logging.
public class ODHClient {
    private static ODHClient instance;

    private final String DATATYPES_ENDPOINT = "/*";
    private final String MEASUREMENT_ENDPOINT = "/latest";

    private final String baseUrl;
    private final HttpClient httpClient;

    private ODHClient() {
        baseUrl = ODHClientConfig.getInstance().getBaseUrl();
        httpClient = HttpClient.newHttpClient();
    }

    public static ODHClient getInstance() {
        if (instance == null) {
            instance = new ODHClient();
        }

        return instance;
    }

    public MobilityEntity getStations(MobilityType mobilityType) {
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(baseUrl + "/" + mobilityType.getId()))
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);
        return mapToMobilityEntity(response);
    }

    public MobilityEntity getStationsAndDatatypes(MobilityType mobilityType) {
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(baseUrl + "/" + mobilityType.getId() + DATATYPES_ENDPOINT))
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);
        return mapToMobilityEntity(response);
    }

    public MobilityEntity getStationsAndDatatypesAndMeasurement(MobilityType mobilityType) {
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
            return response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static MobilityEntity mapToMobilityEntity(HttpResponse<String> response) {
        if(response == null) {
            return null;
        }

        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

        try {
            MobilityEntity mobilityEntity = objectMapper.readValue(response.body(), MobilityEntity.class);
            return mobilityEntity;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
