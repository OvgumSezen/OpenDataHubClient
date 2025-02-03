package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ODHEntity {
    private final Logger logger = LoggerFactory.getLogger(ODHEntity.class);

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return "JsonProcessingException";
        }
    }
}
