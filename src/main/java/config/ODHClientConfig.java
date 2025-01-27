package config;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//TODO: Add logging.
@Getter
public class ODHClientConfig {
    private static ODHClientConfig instance;

    private String baseUrl;

    private ODHClientConfig() {
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            prop.load(fis);
            this.baseUrl = prop.getProperty("mobility.odh.base.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ODHClientConfig getInstance() {
        if(instance == null) {
            instance = new ODHClientConfig();
        }

        return instance;
    }
}
