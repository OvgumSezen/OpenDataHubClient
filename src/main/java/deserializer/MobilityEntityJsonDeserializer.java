package deserializer;

import dao.MobilityEntity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

//TODO: Add custom deserialization logic.
//TODO: Add logging.
public class MobilityEntityJsonDeserializer extends JsonDeserializer<MobilityEntity> {

    @Override
    public MobilityEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        System.out.println(node);

        return null;
    }
}
