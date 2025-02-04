package deserializer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import deserializer.ObjectMapperFactory;
import dao.mobility.MobilityEntity;
import dao.mobility.MobilityType;
import deserializer.ODHEntityDeserializationStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

@AllArgsConstructor
public class MobilityEntityDeserializationStrategy implements ODHEntityDeserializationStrategy {
    private final Logger logger = LoggerFactory.getLogger(MobilityEntityDeserializationStrategy.class);
    private final ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    @Getter
    @Setter
    private MobilityType mobilityType;

    @Override
    public MobilityEntity deserialize(HttpResponse<String> response) {
        String responseBodyString = response.body();

        try{
            JsonNode stationsNode = getJsonNode(responseBodyString);
            MobilityEntity mobilityEntity = getMobilityEntity(stationsNode);

            if(mobilityEntity != null) {
                mobilityEntity.setName(mobilityType.getId());
            }

            logger.info("LOG: deserialized mobility entity: {}", mobilityEntity);

            return mobilityEntity;
        } catch (JsonProcessingException e) {
            logger.error("ERROR: MobilityEntity deserialization JsonProcessingException error: {}", e.getMessage());
            return null;
        }
    }

    private MobilityEntity getMobilityEntity(JsonNode stationsNode) throws JsonProcessingException {
        return objectMapper.treeToValue(stationsNode, MobilityEntity.class);
    }

    private JsonNode getJsonNode(String responseBodyString) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBodyString);
        JsonNode dataNode = rootNode.get("data");
        if(dataNode == null) {
            return null;
        }
        return dataNode.get(mobilityType.getId());
    }
}
