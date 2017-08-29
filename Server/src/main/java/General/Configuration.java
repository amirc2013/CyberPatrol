package General;

import Exceptions.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final String CONFIGURATION_FILE_NAME = "config.properties";
    private static final String MONGO_HOST = "host";
    private static final String MONGO_PORT = "port";

    private Properties prop = null;

    public String getMongoHost() throws ConfigurationException {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(MONGO_HOST);
    }

    public int getMongoPort() throws ConfigurationException {
        if (prop == null) {
            loadProperties();
        }
        return Integer.parseInt(prop.getProperty(MONGO_PORT));
    }

    private Properties loadProperties() throws ConfigurationException {
        try (InputStream input = new FileInputStream(CONFIGURATION_FILE_NAME);) {
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            throw new ConfigurationException("Configuration not found!");
        } catch (IOException e) {
            throw new ConfigurationException("Couldnt read the configuration.");
        }
        return prop;
    }
}
