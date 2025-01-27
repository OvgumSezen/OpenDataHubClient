package deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dao.MobilityEntity;

public class ObjectMapperFactory {
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(MobilityEntity.class, new MobilityEntityJsonDeserializer());

        objectMapper.registerModule(module);

        return objectMapper;
    }
}
