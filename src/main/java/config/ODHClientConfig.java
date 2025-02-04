package config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class ODHClientConfig {
    private static ODHClientConfig instance;

    private final Logger logger = LoggerFactory.getLogger(ODHClientConfig.class);

    private String baseUrl;

    private ODHClientConfig() {
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            prop.load(fis);
            logger.info("LOG: .env properties loaded {}", prop.stringPropertyNames());

            this.baseUrl = prop.getProperty("mobility.odh.base.url");
            logger.info("LOG: loaded baseUrl {}", baseUrl);
        } catch (IOException e) {
            logger.error("LOG: failed to load properties file {}", e.getMessage());
        }
    }

    public static synchronized ODHClientConfig getInstance() {
        if(instance == null) {
            instance = new ODHClientConfig();
        }

        return instance;
    }
}
