package databaseutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {
    final static String configFile = "config.properties";

    public static String readFromConfig(String key) {
        Properties properties = new Properties();

        String value;
        try {
            properties.load((new FileInputStream(configFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        value = properties.getProperty(key);
        return value;

    }
}
